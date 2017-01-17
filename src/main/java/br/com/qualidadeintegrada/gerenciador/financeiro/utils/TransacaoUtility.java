package br.com.qualidadeintegrada.gerenciador.financeiro.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qualidadeintegrada.gerenciador.financeiro.dao.TransacoesDAO;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Conta;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Transacao;

@Service
public class TransacaoUtility {
	
	@Autowired
	private TransacoesDAO transacoesDAO;
	
	
	/*
	 * OPERACOES PADRAO COM OBJETO TRANSACAO
	 */
	
	public void salvar(Transacao transacao) {
		
		this.transacoesDAO.save(transacao);		
	}
	
	public void deletar(Long transacaoId) {
		
		this.transacoesDAO.delete(transacaoId);
	}
	
	
	
	
	/*
	 * BUSCAS COM OBJETO TRANSACAO 
	 */
	
	public List<Transacao> buscarTransacoesPorConta(Conta conta) {
		
		return this.transacoesDAO.findTransacoesByConta(conta);
	}
	
	public List<Transacao> buscarTransacoesConsolidadasPorConta(Conta conta) {
		
		return this.transacoesDAO.findTransacoesConsolidadasByConta(conta);
	}
	
	
	
	
	/*
	 * OPERACOES COM OBJETO TRANSACAO
	 */
	

}
