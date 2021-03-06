package br.com.qualidadeintegrada.gerenciador.financeiro.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qualidadeintegrada.gerenciador.financeiro.dao.ContasDAO;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Conta;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.TipoTransacao;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Transacao;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Usuario;

@Service
public class ContaService {
	
	@Autowired
	private ContasDAO contasDAO;
	
	@Autowired
	private TransacaoService transacaoService;
	
	
	
	
	/*
	 * OPERACOES PADRAO OBJETO CONTA
	 */
	
	public void salva(Conta conta) {
		
		this.contasDAO.save(conta);
	}
	
	public boolean deleta(Long contaId) {
		
		Conta conta = this.contasDAO.findOne(contaId);
		
		List<Transacao> transacoes = new ArrayList<Transacao>();
		transacoes = transacaoService.buscaTransacoesPorConta(conta);
		
		for(Transacao transacao : transacoes) {
			this.transacaoService.deleta(transacao.getId());
		}
		
		this.contasDAO.delete(contaId);
		
		return true;
	}
	
	
	
	
	/*
	 * BUSCAS COM OBJETO CONTA
	 */
	
	public List<Conta> buscaContasPorUsuario(Usuario usuario) {
		
		return this.contasDAO.findContasByUsuario(usuario); 
		
	}
	
	public Conta buscarContaPorId(Long id) {
		
		return this.contasDAO.findOne(id);
	}
	
	
	
	
	
	/*
	 * OPERACOES COM OBJETO CONTA
	 */
	
	public void somaTransacao(Long contaId, BigDecimal valor) {
		
		Conta conta = this.buscarContaPorId(contaId);
		
		conta.setSaldo(conta.getSaldo().add(valor));
		
		this.salva(conta);
		
	}
	
	public void subtraiTransacao(Long contaId, BigDecimal valor) {
		
		Conta conta = this.buscarContaPorId(contaId);
		
		conta.setSaldo(conta.getSaldo().subtract(valor));
		
		this.salva(conta);
		
	}
	
	public List<Conta> atualizaSaldoContas(List<Conta> contas) {
		
		for(Conta conta : contas) {
			conta.setSaldo(this.calculaSaldo(conta));
			this.contasDAO.save(conta);
		}
		
		return contas;
	}
	
	
	private BigDecimal calculaSaldo(Conta conta) {

		List<Transacao> transacoes = new ArrayList<Transacao>();
		transacoes = this.transacaoService.buscaTransacoesConsolidadasPorConta(conta);
		
		BigDecimal saldo = new BigDecimal(0);
		
		for(Transacao transacao : transacoes) {
			
			if(transacao.getTipoTransacao().equals(TipoTransacao.DESPESA)) {
				saldo = saldo.subtract(transacao.getValor());
			} else if (transacao.getTipoTransacao().equals(TipoTransacao.RECEITA)) {
				saldo = saldo.add(transacao.getValor());
			}
		}
		
		return saldo;
		
	}
	

}
