package application;

import java.nio.file.Path;
import java.util.List;

import domain.Capitulo;
import domain.Ebook;

public class Cotuba {
	public void executa(ParametrosCotuba parametros) {
		
		String formato = parametros.getFormato();
		Path diretorioDosMD = parametros.getDiretorioDosMD();
		Path arquivoDeSaida = parametros.getArquivoDeSaida();
		
		RenderizadorMDParaHTML renderizador = RenderizadorMDParaHTML.cria();
		List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);
		
		Ebook ebook = new Ebook();
		ebook.setFormato(formato);
		ebook.setArquivoDeSaida(arquivoDeSaida);
		ebook.setCapitulos(capitulos);
			
			if ("pdf".equals(formato)) {
				GeradorPDF geradorPDF = GeradorPDF.cria();
				geradorPDF.gera(ebook);
			} else if ("epub".equals(formato)) {
				GeradorEPUB geradorEPUB = GeradorEPUB.cria();
				geradorEPUB.gera(ebook);
			} else {
				throw new RuntimeException("Formato do ebook inválido: " + formato);
			}
	}

}
