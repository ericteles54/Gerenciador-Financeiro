package br.com.qualidadeintegrada.gerenciador.financeiro.model;

import java.math.BigDecimal;

public class ContaInfoOnline {
	
	private String mesAno;
	private Conta conta;
	private BigDecimal receitasPeriodo;
	private BigDecimal despesasPeriodo;
	private BigDecimal saldoPeriodo;
	private BigDecimal aplicacaoPeriodo;
	
	
	
	public String getMesAno() {
		return mesAno;
	}
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public BigDecimal getReceitasPeriodo() {
		return receitasPeriodo;
	}
	public void setReceitasPeriodo(BigDecimal receitasPeriodo) {
		this.receitasPeriodo = receitasPeriodo;
	}
	public BigDecimal getDespesasPeriodo() {
		return despesasPeriodo;
	}
	public void setDespesasPeriodo(BigDecimal despesasPeriodo) {
		this.despesasPeriodo = despesasPeriodo;
	}
	public BigDecimal getSaldoPeriodo() {
		return saldoPeriodo;
	}
	public void setSaldoPeriodo(BigDecimal saldoPeriodo) {
		this.saldoPeriodo = saldoPeriodo;
	}
	public BigDecimal getAplicacaoPeriodo() {
		return aplicacaoPeriodo;
	}
	public void setAplicacaoPeriodo(BigDecimal aplicacaoPeriodo) {
		this.aplicacaoPeriodo = aplicacaoPeriodo;
	}
	
}
