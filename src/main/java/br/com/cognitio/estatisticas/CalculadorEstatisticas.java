package br.com.cognitio.estatisticas;

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
		
		for(Capitulo capitulo : ebook.getCapitulos()) {
			
			String html = capitulo.getConteudoHTML();
			
			Document doc = Jsoup.parse(html);
			
			String textoDoCapitulo = doc.body().text();
			
			String[] palavras = textoDoCapitulo.split("\\s+");
			
			for(String palavra : palavras) {
				System.out.println(palavra);
			}
			
		}
		
	}
	
}
