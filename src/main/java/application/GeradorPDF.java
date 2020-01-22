package application;

import domain.Ebook;
import pdf.GeradorPDFComIText;

public interface GeradorPDF {

	void gera(Ebook ebook);
	
	public static GeradorPDF cria() {
		return new GeradorPDFComIText();
	}

}