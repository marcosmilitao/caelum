package application;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;

import domain.Capitulo;
import domain.Ebook;
import domain.FormatoEbook;
import plugin.AoFinalizarGeracao;

public class Cotuba {
	public void executa(ParametrosCotuba parametros,  Consumer<String> acaoPosGeracao) {
		
		FormatoEbook formato = parametros.getFormato();
		Path diretorioDosMD = parametros.getDiretorioDosMD();
		Path arquivoDeSaida = parametros.getArquivoDeSaida();
		
		RenderizadorMDParaHTML renderizador = RenderizadorMDParaHTML.cria();
		List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);
		
		Ebook ebook = new Ebook(formato,arquivoDeSaida,capitulos);

		GeradorEbook gerador = GeradorEbook.cria(formato);

		gerador.gera(ebook);
		
		AoFinalizarGeracao.gerou(ebook, acaoPosGeracao);
		
	}

}
