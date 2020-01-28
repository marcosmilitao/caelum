package tema;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import domain.Capitulo;
import plugin.Tema;

public class AplicadorTema {
	
	public void aplica(Capitulo capitulo) {
		String html = capitulo.getConteudoHTML();
		Document document = Jsoup.parse(html);
		
		List<String> listaDeTemas = Tema.listaTemas();
		for(String css : listaDeTemas) {
			document.select("head").append("<style> " + css + " </style>");		
		}
		capitulo.setConteudoHTML(document.html());
	}

}
