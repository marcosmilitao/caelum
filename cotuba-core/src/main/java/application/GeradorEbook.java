package application;

import domain.Ebook;
import domain.FormatoEbook;

public interface GeradorEbook {
	void gera(Ebook ebook);
	
	public static GeradorEbook cria(FormatoEbook formato) {
		
		GeradorEbook gerador = formato.getGerador();		
		
		return gerador;		
	}
}
