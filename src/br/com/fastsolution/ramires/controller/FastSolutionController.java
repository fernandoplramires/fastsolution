package br.com.fastsolution.ramires.controller;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import br.com.fastsolution.ramires.model.LojasManager;

import com.opencsv.CSVReader;

@Controller
public class FastSolutionController {
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String displayForm() {
		return "uploadfile";
	}
	
	@RequestMapping(value = "/uploadprocess", method = RequestMethod.POST)
	public String upload(HttpServletRequest request, Model map) {	
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("file");
		if (!multipartFile.isEmpty()) {
			InputStream inputStream = null;
			try {
				inputStream = multipartFile.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				String form = "";
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					form = form + line + '\n';
				}
				LojasManager lojasManager = new LojasManager();
				StringReader stringReader = new StringReader(form);
				CSVReader reader = new CSVReader(stringReader, '\t');
				int headerCheck = 0;
				for (String [] nextLine = reader.readNext(); nextLine != null; nextLine = reader.readNext()) {
					if (headerCheck == 0) {
						String header = nextLine[0]+nextLine[1]+nextLine[2];
						if (header.toLowerCase().equals("filialperiodototal")) {
							headerCheck++;
						} else {
							String error = "Arquivo invalido";
							map.addAttribute("error", error);
							return "uploadfileerror";	
						}
					} else {
						String filial = nextLine[0].trim();
						String mes = nextLine[1].trim().toLowerCase();
						double valor = Double.parseDouble(nextLine[2].trim().replaceAll("\\.","").replace(",","."));
						lojasManager.addMovimentacao(filial, mes, valor);
					}
				}
				Map<String,String> relatorioTrimestral = lojasManager.getRelatorioTrimestral();
				System.out.println("------------[ Relatorio Trimestral ]------------");
				for (String chave : relatorioTrimestral.keySet()) {
					System.out.println("[" + chave + "]=[" + relatorioTrimestral.get(chave) + "]");
				}
				System.out.println("------------------------------------------------");
				map.addAttribute("relatorioTrimestral", relatorioTrimestral);
				return "uploadfilesuccess";
			} catch (FileNotFoundException e) {
				String error = e.toString();
				map.addAttribute("error", error);
				return "uploadfileerror";	
			}
			catch (IOException e) {
				String error = e.toString();
				map.addAttribute("error", error);
				return "uploadfileerror";	
			}
		} else {
			String error = "Arquivo vazio ou inexistente";
			map.addAttribute("error", error);
			return "uploadfileerror";	
		}
	}
}