package br.com.qualidadeintegrada.gerenciador.financeiro.utils;

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
public class ContaUtility {
	
	@Autowired
	private ContasDAO contasDAO;
	
	@Autowired
	private TransacaoUtility transacaoUtility;
	
	
	
	
	/*
	 * OPERACOES PADRAO OBJETO CONTA
	 */
	
	public void salvar(Conta conta) {
		
		this.contasDAO.save(conta);
	}
	
	
	
	
	
	/*
	 * BUSCAS COM OBJETO CONTA
	 */
	
	public List<Conta> buscarContasPorUsuario(Usuario usuario) {
		
		return this.contasDAO.findContasByUsuario(usuario); 
		
	}
	
	
	
	
	
	/*
	 * OPERACOES COM OBJETO CONTA
	 */
	
	public List<Conta> atualizaSaldoContas(List<Conta> contas) {
		
		for(Conta conta : contas) {
			conta.setSaldo(this.calculaSaldo(conta));
			this.contasDAO.save(conta);
		}
		
		return contas;
	}
	
	
	private BigDecimal calculaSaldo(Conta conta) {

		List<Transacao> transacoes = new ArrayList<Transacao>();
		transacoes = this.transacaoUtility.buscarTransacoesConsolidadasPorConta(conta);
		
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
