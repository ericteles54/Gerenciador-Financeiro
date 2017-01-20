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
		Transacao transacaoSalvar;		
		for(int i = 1; i <= transacao.getRepeticoes(); i++) {
			
			if(i > 1) {
				transacaoSalvar = new Transacao();
				transacaoSalvar.setValor(transacao.getValor());
				transacaoSalvar.setTipoTransacao(transacao.getTipoTransacao());
				transacaoSalvar.setCorTransacao(transacao.getCorTransacao());
				transacaoSalvar.setImgTransacao(transacao.getImgTransacao());
				transacaoSalvar.setDescricao(transacao.getDescricao());			
				transacaoSalvar.setData(data.getTime());
				transacaoSalvar.setConsolidada(transacao.isConsolidada());
				transacaoSalvar.setConta(transacao.getConta());		
				this.transacoesDAO.save(transacaoSalvar);
			}
			else {
				this.transacoesDAO.save(transacao);
			}
			
			
			data.add(Calendar.MONTH, 1);
		}
						
	}
	
	public void deleta(Long transacaoId) {
		
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
	

}
