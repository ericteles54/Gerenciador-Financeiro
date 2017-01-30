package br.com.qualidadeintegrada.gerenciador.financeiro.model;

public enum TipoTransacao {
	
	DESPESA("Despesa"),
	RECEITA("Receita"),
	TRANSFERENCIA("Transferencia");
	
	private String descricao;
	
	TipoTransacao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
