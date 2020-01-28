package br.com.paradizo.tema;

import plugin.Tema;

public class TemaParadizo implements Tema{

	@Override
	public String cssDoTema() {

		return FileUtils.getResourceContents("/tema.css");
	}	
	
}
