package br.com.fastsolution.ramires.model;

import java.util.HashMap;
import java.util.Map;

public class LojasManager {

	//Manager de lojas;
	private final Map<String,Loja> lojas;
	
	//Manager de relatorios;
	private final Map<String,Relatorio> relatorioDeLojas;
	private final Map<String,String> relatorioTrimestral;
	
	//Manager de lojas e de relatorios;
	public LojasManager() {
		this.lojas = new HashMap<String,Loja>();
		this.relatorioDeLojas = new HashMap<String,Relatorio>();
		this.relatorioTrimestral = new HashMap<String,String>();
	}
	
	//Manager de lojas;
	public void addMovimentacao(String cidade, String periodo, double valor) {
		if (this.lojas.containsKey(cidade)) {
			Loja loja = this.lojas.get(cidade);
			loja.addMovimentacao(periodo, valor);
		} else {
			Loja newLoja = new Loja();
			newLoja.setCidade(cidade);
			newLoja.addMovimentacao(periodo, valor);
			this.lojas.put(cidade,newLoja);
		}
	}
	
	//Relatorios Trimestrais;
	public Map<String,String> getRelatorioTrimestral() {
		this.genRelatorioDeLojas();
		this.relatorioTrimestral.put("Filial que teve o maior crescimento?", this.getCrescimentoPrctTotal());
		this.relatorioTrimestral.put("Filial que mais vendeu no periodo?", this.getValorTotalDeVendas());
		this.relatorioTrimestral.put("Filial que teve maior queda nas vendas?", this.getQuedaPrctTotal());
		this.relatorioTrimestral.put("Mes em que a empresa mais vendeu?", this.getMesQueMaisVendeu());
		return this.relatorioTrimestral;
	}
	
	//Trigger para geracao dos relatorios;
	private void genRelatorioDeLojas() {
		for (String cidadeDaLoja: this.lojas.keySet()) {
			Loja loja = this.lojas.get(cidadeDaLoja);
			Relatorio relatorio = new Relatorio(loja);
			relatorioDeLojas.put(relatorio.getLoja().getCidade(), relatorio);
		}
	}
	
	//Filial que teve o maior crescimento;
	private String getCrescimentoPrctTotal() {
		String filialVencedora = "NENHUMA";
		if (!this.relatorioDeLojas.isEmpty()) {
			double crescimentoPrctReferencia = 0;
			for (String filial: this.relatorioDeLojas.keySet()) {
				double crescimentoPrct = this.relatorioDeLojas.get(filial).getCrescimentoPrctTotal();
				//System.out.println("[TEST]getCrescimentoPrctTotal(): filial=[" + filial + "]=[" + crescimentoPrct + "]");
				if (crescimentoPrct > crescimentoPrctReferencia) {
					filialVencedora = filial;
					crescimentoPrctReferencia = crescimentoPrct;
				}
			}
		}
		return filialVencedora;	
	}
	
	//Filial que mais vendeu no período;
	private String getValorTotalDeVendas() {
		String filialVencedora = "NENHUMA";
		if (!this.relatorioDeLojas.isEmpty()) {
			double valorDeVendasReferencia = 0;
			for (String filial: this.relatorioDeLojas.keySet()) {
				double valorDeVendas = this.relatorioDeLojas.get(filial).getValorTotalDeVendas();
				//System.out.println("[TEST]getValorTotalDeVendas(): filial=[" + filial + "]=[" + valorDeVendas + "]");
				if (valorDeVendas > valorDeVendasReferencia) {
					filialVencedora = filial;
					valorDeVendasReferencia = valorDeVendas;
				}
			}
		}
		return filialVencedora;	
	}
	
	//Filial que teve a maior queda nas vendas;
	private String getQuedaPrctTotal() {
		String filialVencedora = "NENHUMA";
		if (!this.relatorioDeLojas.isEmpty()) {
			double quedaPrctReferencia = 0;
			for (String filial: this.relatorioDeLojas.keySet()) {
				double quedaPrct = this.relatorioDeLojas.get(filial).getQuedaPrctTotal();
				//System.out.println("[TEST]getQuedaPrctTotal(): filial=[" + filial + "]=[" + quedaPrct + "]");
				if (quedaPrct < quedaPrctReferencia) {
					filialVencedora = filial;
					quedaPrctReferencia = quedaPrct;
				}
			}
		}
		return filialVencedora;	
	}
	
	//Mês em que a empresa mais vendeu.
	private String getMesQueMaisVendeu() {
		String mesVencedor = "NENHUM";
		if (!this.relatorioDeLojas.isEmpty()) {
			Map<String,Double> vendasPorMesGeral = new HashMap<String,Double>();
			for (String filial: this.relatorioDeLojas.keySet()) {
				Relatorio relatorio = this.relatorioDeLojas.get(filial);
				Map<String,Double> vendasPorMesDestaLoja = relatorio.getLoja().getMovimentacao();
				for (String mes: vendasPorMesDestaLoja.keySet()) {
					double contagem = 0;
					if (vendasPorMesGeral.containsKey(mes)) {
						contagem = vendasPorMesGeral.get(mes)+vendasPorMesDestaLoja.get(mes);
					} else {
						contagem = vendasPorMesDestaLoja.get(mes);
					}
					vendasPorMesGeral.put(mes, contagem);
				}
			}
			double contadorReferencia = 0;
			for (String mes: vendasPorMesGeral.keySet()) {
				//System.out.println("[TEST]getMesQueMaisVendeu(): mes=[" + mes + "]=[" + vendasPorMesGeral.get(mes) + "]");
				double vendasDesteMes = vendasPorMesGeral.get(mes);
				if (vendasDesteMes > contadorReferencia) {
					mesVencedor = mes;
					contadorReferencia = vendasDesteMes;
				}
			}
		}
		return mesVencedor;	
	}
}