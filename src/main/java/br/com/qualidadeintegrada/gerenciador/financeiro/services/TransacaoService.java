package br.com.qualidadeintegrada.gerenciador.financeiro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qualidadeintegrada.gerenciador.financeiro.dao.TransacoesDAO;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Conta;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Transacao;

@Service
public class TransacaoService {
	
	@Autowired
	private TransacoesDAO transacoesDAO;
	
	
	/*
	 * OPERACOES PADRAO COM OBJETO TRANSACAO
	 */
	
	public void salva(Transacao transacao) {
		
		this.transacoesDAO.save(transacao);		
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
		
		return this.transacoesDAO.findTransacoesConsolidadasByConta(conta);
	}
	
	public Transacao buscaTransacaoPorId(Long id) {
		
		return this.transacoesDAO.findOne(id);
	}
	
	
	
	
	/*
	 * OPERACOES COM OBJETO TRANSACAO
	 */
	

}
