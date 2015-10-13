package br.com.fastsolution.ramires.model;

import java.util.Map;

public class Relatorio {
	
	//Relatorio de loja;
	Loja loja;
	double crescimentoPrctTotal;
	double valorTotalDeVendas;
	double quedaPrctTotal;
	String mesQueMaisVendeu;
	
	//Relatorio de loja;
	public Relatorio(Loja loja) {
		this.loja = loja;
	}
	public Loja getLoja() {
		return this.loja;
	}
	
	//Crescimento de vendas no periodo;
	public double getCrescimentoPrctTotal() {
		Map<String,Double> movimentacao = this.loja.getMovimentacao();
		if (!movimentacao.isEmpty()) {
			double valorDeVendasRefInicio = 0;
			double valorDeVendasRefFinal = 0;
			this.crescimentoPrctTotal = 0;
			for (String periodo: movimentacao.keySet()) {
				if (periodo.equals("janeiro") || periodo.equals("abril") || periodo.equals("julho") || periodo.equals("outubro")) {
					valorDeVendasRefInicio = movimentacao.get(periodo);
				}
				if (periodo.equals("março") || periodo.equals("junho") || periodo.equals("setembro") || periodo.equals("dezembro")) {
					valorDeVendasRefFinal = movimentacao.get(periodo);
				}
			}
			double base = valorDeVendasRefFinal/valorDeVendasRefInicio;
			double exponencial = (double) 1/movimentacao.size();
			this.crescimentoPrctTotal = Math.pow(base,exponencial);
			this.crescimentoPrctTotal = this.crescimentoPrctTotal-1;
			this.crescimentoPrctTotal = this.crescimentoPrctTotal*100;
			//System.out.println("[TEST]getCrescimentoPrctTotal inicio=[" + valorDeVendasRefInicio + "] fim=[" + valorDeVendasRefFinal + "] tx=[" + this.crescimentoPrctTotal + "]");
		}
		if (this.crescimentoPrctTotal <= 0) {
			this.crescimentoPrctTotal = 0;
		}
		return this.crescimentoPrctTotal;
	}
	
	//Total de vendas no periodo;
	public double getValorTotalDeVendas() {
		Map<String,Double> movimentacao = this.loja.getMovimentacao();
		if (!movimentacao.isEmpty()) {
			for (String periodo: movimentacao.keySet()) {
				double valorDeVendas = movimentacao.get(periodo);
				this.valorTotalDeVendas = this.valorTotalDeVendas + valorDeVendas;
			}
		}
		return this.valorTotalDeVendas;
	}
	
	//Queda nas vendas no periodo;
	public double getQuedaPrctTotal() {
		Map<String,Double> movimentacao = this.loja.getMovimentacao();
		if (!movimentacao.isEmpty()) {
			double valorDeVendasRefInicio = 0;
			double valorDeVendasRefFinal = 0;
			this.quedaPrctTotal = 0;
			for (String periodo: movimentacao.keySet()) {
				if (periodo.equals("janeiro") || periodo.equals("abril") || periodo.equals("julho") || periodo.equals("outubro")) {
					valorDeVendasRefInicio = movimentacao.get(periodo);
				}
				if (periodo.equals("março") || periodo.equals("junho") || periodo.equals("setembro") || periodo.equals("dezembro")) {
					valorDeVendasRefFinal = movimentacao.get(periodo);
				}
			}
			double base = valorDeVendasRefFinal/valorDeVendasRefInicio;
			double exponencial = (double) 1/movimentacao.size();
			this.quedaPrctTotal = Math.pow(base,exponencial);
			this.quedaPrctTotal = this.quedaPrctTotal-1;
			this.quedaPrctTotal = this.quedaPrctTotal*100;
			//System.out.println("[TEST]getQuedaPrctTotal inicio=[" + valorDeVendasRefInicio + "] fim=[" + valorDeVendasRefFinal + "] tx=[" + this.quedaPrctTotal + "]");
		}		
		if (this.quedaPrctTotal >= 0) {
			this.quedaPrctTotal = 0;
		}
		return this.quedaPrctTotal;
	}
	
	//Mês em que a loja mais vendeu.
	public String getMesQueMaisVendeu() {
		Map<String,Double> movimentacao = this.loja.getMovimentacao();
		if (!movimentacao.isEmpty()) {
			double valorDeVendasReferencia = 0;
			for (String periodo: movimentacao.keySet()) {
				double valorDeVendas = movimentacao.get(periodo);
				if (valorDeVendas > valorDeVendasReferencia) {
					mesQueMaisVendeu = periodo;
				}
				valorDeVendasReferencia = valorDeVendas;
			}
		}
		return mesQueMaisVendeu;
	}
}
