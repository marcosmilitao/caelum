package br.com.cognitio.estatisticas;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import domain.Capitulo;
import domain.Ebook;
import plugin.Plugin;

public class CalculadorEstatisticas implements Plugin {

	@Override
	public String cssDoTema() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aposGeracao(Ebook ebook) {
		
		Map<String,Integer> contagemPalavras = new HashMap<>();
		
		for(Capitulo capitulo : ebook.getCapitulos()) {
			
			String html = capitulo.getConteudoHTML();
			
			Document doc = Jsoup.parse(html);
			
			String textoDoCapitulo = doc.body().text();
			
			String textoDoCapituloSemPontuacao = textoDoCapitulo.replaceAll("\\p{Punct}", " ");
			
			String textoDoCapituloSemAcentos = Normalizer.normalize(textoDoCapituloSemPontuacao, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
			
			String[] palavras = textoDoCapituloSemAcentos.split("\\s+");
			
						
			for(String palavra : palavras) {
				String emMaiusculas = palavra.toUpperCase();
				
				contagemPalavras.put(emMaiusculas, 1);		
				
			}
			
			for(Map.Entry<String, Integer>contagem : contagemPalavras.entrySet()) {
				
				String palavra = contagem.getKey();
				
				Integer ocorrencias = contagem.getValue();
				
				System.out.println(palavra + ": " + ocorrencias);
			}
			
		}
		
	}
	
}
