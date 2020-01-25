package br.com.paradizo.tema;

import domain.Ebook;
import plugin.Plugin;

public class TemaParadizo implements Plugin{

	@Override
	public String cssDoTema() {

		return FileUtils.getResourceContents("/tema.css");
	}

	@Override
	public void aposGeracao(Ebook ebook) {
		
	}	
	
}
