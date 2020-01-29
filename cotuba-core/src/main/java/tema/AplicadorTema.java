package tema;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import plugin.Tema;

public class AplicadorTema {
	
	public String aplica(String html) {
		
		Document document = Jsoup.parse(html);
		
		List<String> listaDeTemas = Tema.listaTemas();
		for(String css : listaDeTemas) {
			document.select("head").append("<style> " + css + " </style>");		
		}

		return document.html();
	}

}
