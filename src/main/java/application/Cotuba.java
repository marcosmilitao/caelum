package application;

import java.nio.file.Path;
import java.util.List;

import domain.Capitulo;
import domain.Ebook;
import domain.FormatoEbook;
import plugin.Plugin;

public class Cotuba {
	public void executa(ParametrosCotuba parametros) {
		
		FormatoEbook formato = parametros.getFormato();
		Path diretorioDosMD = parametros.getDiretorioDosMD();
		Path arquivoDeSaida = parametros.getArquivoDeSaida();
		
		RenderizadorMDParaHTML renderizador = RenderizadorMDParaHTML.cria();
		List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);
		
		Ebook ebook = new Ebook();
		ebook.setFormato(formato);
		ebook.setArquivoDeSaida(arquivoDeSaida);
		ebook.setCapitulos(capitulos);
		GeradorEbook gerador = GeradorEbook.cria(formato);

		gerador.gera(ebook);
		
		Plugin.gerou(ebook);
		
	}

}
