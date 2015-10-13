package br.com.fastsolution.ramires.model;

import java.util.HashMap;
import java.util.Map;

public class Loja {
	
	private String cidade;
	private final Map<String, Double> movimentacao;
	
	public Loja() { 
		this.movimentacao  = new HashMap<String, Double>();
	}	
	public String getCidade() {
		return this.cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public Map<String,Double> getMovimentacao() {
		return this.movimentacao;
	}
	public void addMovimentacao(String periodo, double valor) {
		this.movimentacao.put(periodo, Double.valueOf(valor));
	}
}
