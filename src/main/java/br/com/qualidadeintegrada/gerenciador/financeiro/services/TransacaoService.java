package br.com.qualidadeintegrada.gerenciador.financeiro.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qualidadeintegrada.gerenciador.financeiro.dao.TransacoesDAO;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Conta;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.TipoTransacao;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Transacao;

@Service
public class TransacaoService {
	
	@Autowired
	private TransacoesDAO transacoesDAO;
	
	@Autowired
	private ContaService contaService;
	
	
	/*
	 * OPERACOES PADRAO COM OBJETO TRANSACAO
	 */
	
	public void salva(Transacao transacao) {
				
		if(transacao.getTipoTransacao().equals(TipoTransacao.DESPESA)) {
			transacao.setCorTransacao("#660000");
			transacao.setImgTransacao("images/despesa.png");
		}
		else if (transacao.getTipoTransacao().equals(TipoTransacao.RECEITA)) {
			transacao.setCorTransacao("#006600");
			transacao.setImgTransacao("images/receita.png");
		}
		else {
			transacao.setCorTransacao("#002699");
			transacao.setImgTransacao("images/transferencia.png");
		}
				
		Calendar data = Calendar.getInstance();
		data.setTime(transacao.getData());
		
		
		// Se ao adicionar a transacao o numero de repetições for maior que 1
		// entra no loop
		if(transacao.getRepeticoes() > 0) {
			Transacao transacaoSalvar;		
			for(int i = 0; i < transacao.getRepeticoes(); i++) {
				
				transacaoSalvar = new Transacao();
				transacaoSalvar.setValor(transacao.getValor());
				transacaoSalvar.setTipoTransacao(transacao.getTipoTransacao());
				transacaoSalvar.setCorTransacao(transacao.getCorTransacao());
				transacaoSalvar.setImgTransacao(transacao.getImgTransacao());
				transacaoSalvar.setDescricao(transacao.getDescricao());			
				transacaoSalvar.setData(data.getTime());
				transacaoSalvar.setConsolidada(transacao.isConsolidada());
				transacaoSalvar.setConta(transacao.getConta());
							
				this.adicionaTransacaoSaldoConta(transacaoSalvar);
				this.transacoesDAO.save(transacaoSalvar);				
				
				// Incrementa 1 mês na data
				data.add(Calendar.MONTH, 1);
			}
		}				
		else { // Senao somente adiciona a transacao ou faz o update			
						
			this.adicionaTransacaoSaldoConta(transacao);		
			this.transacoesDAO.save(transacao);			
			
		}
		
		
						
	}
	
	public void deleta(Long transacaoId) {
		
		this.removeTransacaoSaldoConta(this.buscaTransacaoPorId(transacaoId));
		this.transacoesDAO.delete(transacaoId);
	}
	
	
	
	
	/*
	 * BUSCAS COM OBJETO TRANSACAO 
	 */
	
	public List<Transacao> buscaTransacoesPorConta(Conta conta) {
		
		return this.transacoesDAO.findTransacoesByConta(conta);
	}
	
	public List<Transacao> buscaTransacoesConsolidadasPorConta(Conta conta) {
		
		return this.transacoesDAO.findValorTransacoesConsolidadasByConta(conta);
	}
	
	public Transacao buscaTransacaoPorId(Long id) {
		
		return this.transacoesDAO.findOne(id);
	}
	
	public List<Transacao> buscaTransacoesPorMesAnoConta(int mes, int ano, Conta conta) {
		
		List<Transacao> transacoes = new ArrayList<Transacao>();
		transacoes = this.buscaTransacoesPorConta(conta);
		
		Calendar cal = Calendar.getInstance();
		
		List<Transacao> transacoesSaida = new ArrayList<Transacao>();
		for(Transacao transacao : transacoes) {
			
			cal.setTime(transacao.getData());
			int anoTransacao = cal.get(Calendar.YEAR);
			int mesTransacao = cal.get(Calendar.MONTH);
			if((anoTransacao == ano) && (mesTransacao == mes)) {
				transacoesSaida.add(transacao);
			}
		}
		
		return transacoesSaida;
	}
	
	
	
	/*
	 * OPERACOES COM OBJETO TRANSACAO
	 */
	
	private void adicionaTransacaoSaldoConta(Transacao transacao) {
				
	
		if(transacao.getId() == 0) {
			
			// É uma transação nova
			if(transacao.isConsolidada()) {				
								
				if(transacao.getTipoTransacao().equals(TipoTransacao.DESPESA)) {
					this.contaService.subtraiTransacao(transacao.getConta().getId(), transacao.getValor());
				} else if (transacao.getTipoTransacao().equals(TipoTransacao.RECEITA)) {
					this.contaService.somaTransacao(transacao.getConta().getId(), transacao.getValor());
				}				
			}
		}
		else {
						
			// É um update na transacao - entao o valor dela é removido se estava consolidada
			// antes de operar na transacao
			this.removeTransacaoSaldoConta(transacao);
			
			if(transacao.isConsolidada()) {				
				if(transacao.getTipoTransacao().equals(TipoTransacao.DESPESA)) {
					this.contaService.subtraiTransacao(transacao.getConta().getId(), transacao.getValor());
				} else if (transacao.getTipoTransacao().equals(TipoTransacao.RECEITA)) {
					this.contaService.somaTransacao(transacao.getConta().getId(), transacao.getValor());
				}				
			}
			
		}
	}
	
	
	private void removeTransacaoSaldoConta(Transacao transacao) {
		
		Transacao transacaoSalvaNoBanco = this.buscaTransacaoPorId(transacao.getId());
		if(transacaoSalvaNoBanco.isConsolidada()) {		
						
			if(transacaoSalvaNoBanco.getTipoTransacao().equals(TipoTransacao.DESPESA)) {
				this.contaService.somaTransacao(transacaoSalvaNoBanco.getConta().getId(), transacaoSalvaNoBanco.getValor());
			}
			else if (transacaoSalvaNoBanco.getTipoTransacao().equals(TipoTransacao.RECEITA)) {
				this.contaService.subtraiTransacao(transacaoSalvaNoBanco.getConta().getId(), transacaoSalvaNoBanco.getValor());
			}
		}		
	}

}
