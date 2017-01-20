package br.com.qualidadeintegrada.gerenciador.financeiro.model;

public class AnoMes implements Comparable<AnoMes> {
	
	private int mes;
	private int ano;
	private String mesNome;
	private String mesAnoString;
	
	
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getMesNome() {
		return mesNome;
	}
	public void setMesNome(String mesNome) {
		this.mesNome = mesNome;
	}
	public String getMesAnoString() {
		return mesAnoString;
	}
	public void setMesAnoString(String mesAnoString) {
		this.mesAnoString = mesAnoString;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		result = prime * result + mes;
		result = prime * result + ((mesAnoString == null) ? 0 : mesAnoString.hashCode());
		result = prime * result + ((mesNome == null) ? 0 : mesNome.hashCode());
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnoMes other = (AnoMes) obj;
		if (ano != other.ano)
			return false;
		if (mes != other.mes)
			return false;
		if (mesAnoString == null) {
			if (other.mesAnoString != null)
				return false;
		} else if (!mesAnoString.equals(other.mesAnoString))
			return false;
		if (mesNome == null) {
			if (other.mesNome != null)
				return false;
		} else if (!mesNome.equals(other.mesNome))
			return false;
		return true;
	}
	
	
	@Override
	public int compareTo(AnoMes outro) {
		
		if(outro.getAno() != getAno()) {
			return Integer.compare(outro.getAno(), getAno());
		}
		else {
			return Integer.compare(outro.getMes(), getMes());
		}		
	}
	
	
	
	
	

}
