package application;

import domain.Ebook;
import epub.GeradorEPUBComEpubLib;

public interface GeradorEPUB {

	void gera(Ebook ebook);
	
	public static GeradorEPUB cria() {
		return new GeradorEPUBComEpubLib();
	}

}