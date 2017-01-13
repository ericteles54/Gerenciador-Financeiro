package br.com.qualidadeintegrada.gerenciador.financeiro.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Transacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	private BigDecimal valor;
	
	@NotNull
	private TipoTransacao tipoTransacao;
	
	@NotNull
	private String descricao;
	
	@NotNull
	private Date data;
	
	@NotNull
	private boolean consolidada;
	
	@NotNull
	@ManyToOne
	private Conta conta;
	
	
	public Transacao() {
		
	}
	
	public Transacao(BigDecimal valor, TipoTransacao tipoTransacao, String descricao, Date data, boolean consolidada, Conta conta) {
		this.valor = valor;
		this.tipoTransacao = tipoTransacao;
		this.descricao = descricao;
		this.data = data;
		this.consolidada = consolidada;
		this.conta = conta;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoTransacao getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(TipoTransacao tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isConsolidada() {
		return consolidada;
	}

	public void setConsolidada(boolean consolidada) {
		this.consolidada = consolidada;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
}
