package br.com.qualidadeintegrada.gerenciador.financeiro.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Transacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	private BigDecimal valor;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoTransacao tipoTransacao;
	
	@NotNull
	private String corTransacao;
	
	@NotNull
	private String imgTransacao;
	
	@NotNull
	private String descricao;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@NotNull
	private boolean consolidada;
	
	@NotNull
	private boolean aplicacao;
	
	@NotNull
	@ManyToOne
	private Conta conta;
	
	@Transient
	private int repeticoes;
	
	
	public Transacao() {
		
	}
	
	public Transacao(BigDecimal valor, TipoTransacao tipoTransacao, String corTransacao, String imgTransacao, String descricao, Date data, boolean consolidada, Conta conta) {
		this.valor = valor;
		this.tipoTransacao = tipoTransacao;
		this.corTransacao = corTransacao;
		this.imgTransacao = imgTransacao;
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
	
	public String getCorTransacao() {
		return corTransacao;
	}

	public void setCorTransacao(String corTransacao) {
		this.corTransacao = corTransacao;
	}
		
	public String getImgTransacao() {
		return imgTransacao;
	}

	public void setImgTransacao(String imgTransacao) {
		this.imgTransacao = imgTransacao;
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
	
	
	public boolean isAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(boolean aplicacao) {
		this.aplicacao = aplicacao;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public int getRepeticoes() {
		return repeticoes;
	}

	public void setRepeticoes(int repeticoes) {
		this.repeticoes = repeticoes;
	}
	
}
