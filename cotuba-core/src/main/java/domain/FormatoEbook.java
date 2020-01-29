package domain;

import pdf.GeradorPDF;
import application.GeradorEbook;
import epub.GeradorEPUB;

public enum FormatoEbook {
	PDF(new GeradorPDF()),
	EPUB(new GeradorEPUB());
	
	private GeradorEbook gerador;
	
	private FormatoEbook(GeradorEbook gerador) {
		this.gerador = gerador;
	}
	
	public GeradorEbook getGerador() {
		return gerador;
	}
}
