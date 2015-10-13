package br.com.fastsolution.ramires.tester;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import br.com.fastsolution.ramires.model.LojasManager;

import com.opencsv.CSVReader;

public class TesterParser {
	
	public static void main(String[] args) {
		String form = input();
		//parser(form);
		//lojaManagerMovimentacoes();
		parserAndProcess(form);
	}
	
	public static String input() {
		String form = "Filial	Periodo	Total" + '\n'
				+ "São Paulo	Janeiro	14.558,00 " + '\n'
				+ "Rio de Janeiro	Janeiro	125.635,00" + '\n'
				+ "Fortaleza	Janeiro	8.596,00 " + '\n'
				+ "Goiânia	Janeiro	6.565,00 " + '\n'
				+ "Belo Horizonte	Janeiro	8.547,00 " + '\n'
				+ "Salvador	Janeiro	745,00 " + '\n'
				+ "Brasilia	Janeiro	23,00 " + '\n'
				+ "São Paulo	Fevereiro	85.744,00 " + '\n'
				+ "Rio de Janeiro	Fevereiro	5.255,00 " + '\n'
				+ "Fortaleza	Fevereiro	858.854,00 " + '\n'
				+ "Goiânia	Fevereiro	6.366,00 " + '\n'
				+ "Belo Horizonte	Fevereiro	2.521,00 " + '\n'
				+ "Salvador	Fevereiro	88.963,00 " + '\n'
				+ "Brasilia	Fevereiro	415.854,00 " + '\n'
				+ "São Paulo	Março	478.996,00 " + '\n'
				+ "Rio de Janeiro	Março	5.552,00 " + '\n'
				+ "Fortaleza	Março	253.364,00 " + '\n'
				+ "Goiânia	Março	2.512,00 " + '\n'
				+ "Belo Horizonte	Março	2.455.586,00 " + '\n'
				+ "Salvador	Março	25.553,00 " + '\n'
				+ "Brasilia	Março	28.522,00 ";
		return form;
	}
	
	public static void parser(String form) {
		CSVReader reader;
		try {
			reader = new CSVReader( new StringReader(form), '\t' );
			String [] nextLine;
			while ((nextLine = reader.readNext()) != null) {
			    System.out.println("[" + nextLine[0].trim() + "]-[" + nextLine[1].trim() + "]-[" + nextLine[2].trim() + "]");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void lojaManagerMovimentacoes() {
		LojasManager lojasManager = new LojasManager();
		lojasManager.addMovimentacao("São Paulo", "janeiro", 14558.00);
		lojasManager.addMovimentacao("Rio de Janeiro", "janeiro", 125635.00);
		lojasManager.addMovimentacao("Fortaleza", "janeiro", 8596.00);
		lojasManager.addMovimentacao("Goiânia", "janeiro", 6565.00);
		lojasManager.addMovimentacao("Belo Horizonte", "janeiro", 8547.00);
		lojasManager.addMovimentacao("Salvador", "janeiro", 25553.00);
		lojasManager.addMovimentacao("Brasilia", "janeiro", 23.00);
		lojasManager.addMovimentacao("São Paulo", "fevereiro", 85744.00);
		lojasManager.addMovimentacao("Rio de Janeiro", "fevereiro", 5255.00);
		lojasManager.addMovimentacao("Fortaleza", "fevereiro", 858854.00);
		lojasManager.addMovimentacao("Goiânia", "fevereiro", 6366.00);
		lojasManager.addMovimentacao("Belo Horizonte", "fevereiro", 2521.00);
		lojasManager.addMovimentacao("Salvador", "fevereiro", 88963.00);
		lojasManager.addMovimentacao("Brasilia", "fevereiro", 415854.00);
		lojasManager.addMovimentacao("São Paulo", "março", 478996.00);
		lojasManager.addMovimentacao("Rio de Janeiro", "março", 5552.00);
		lojasManager.addMovimentacao("Fortaleza", "março", 253364.00);
		lojasManager.addMovimentacao("Goiânia", "março", 2512.00);
		lojasManager.addMovimentacao("Belo Horizonte", "março", 2455586.00);
		lojasManager.addMovimentacao("Salvador", "março", 745.00);
		lojasManager.addMovimentacao("Brasilia", "março", 28522.00);
		System.out.println("main.lojaManagerMovimentacoes: lojasManager.getRelatorioTrimestral()");
		Map<String,String> relatorioTrimestral = lojasManager.getRelatorioTrimestral();
		for (String chave : relatorioTrimestral.keySet()) {
			System.out.println("[" + chave + "]=[" + relatorioTrimestral.get(chave) + "]");
		}
	}
	
	public static void parserAndProcess(String form) {
		LojasManager lojasManager = new LojasManager();
		CSVReader reader;
		try {
			reader = new CSVReader( new StringReader(form), '\t');
			int headerCheck = 0;
			for (String [] nextLine = reader.readNext(); nextLine != null; nextLine = reader.readNext()) {
				//System.out.println("[" + nextLine[0].trim() + "]-[" + nextLine[1].trim() + "]-[" + nextLine[2].trim() + "]");
				if (headerCheck == 0) {
					String header = nextLine[0]+nextLine[1]+nextLine[2];
					if (header.toLowerCase().equals("filialperiodototal")) {
						headerCheck++;
					} else {
						return;
					}
				} else {
					String filial = nextLine[0].trim();
					String mes = nextLine[1].trim().toLowerCase();
					double valor = Double.parseDouble(nextLine[2].trim().replaceAll("\\.","").replace(",","."));
					lojasManager.addMovimentacao(filial, mes, valor);
					System.out.println("[" + nextLine[0].trim() + "]-[" + nextLine[1].trim() + "]-[" + nextLine[2].trim() + "]");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("main.parserAndProcess: lojasManager.getRelatorioTrimestral()");
		Map<String,String> relatorioTrimestral = lojasManager.getRelatorioTrimestral();
		for (String chave : relatorioTrimestral.keySet()) {
			System.out.println("[" + chave + "]=[" + relatorioTrimestral.get(chave) + "]");
		}
	}
}
