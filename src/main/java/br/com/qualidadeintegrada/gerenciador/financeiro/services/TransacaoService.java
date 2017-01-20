package br.com.qualidadeintegrada.gerenciador.financeiro.services;

import java.util.ArrayList;
import java.util.Calendar;
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
