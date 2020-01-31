package cotuba.web.application;

import java.nio.file.Path;

import org.springframework.stereotype.Service;

import application.Cotuba;
import application.ParametrosCotuba;
import application.RepositorioDeMDs;
import cotuba.web.domain.Livro;
import domain.FormatoEbook;


@Service
public class GeracaoDeLivros {
	
	private final CadastroDeLivros livros;
	
	public GeracaoDeLivros(CadastroDeLivros livros) {
		this.livros = livros;
	}
	
	public Path geraLivro(Long id, FormatoEbook formato) {
		
		Livro livro = livros.detalha(id);
		
		Cotuba cotuba = new Cotuba();
		
		ParametrosCotuba parametros = new ParametrosCotubaWeb(formato);
		RepositorioDeMDs mdsDoBD = new MDsDoBancoDeDados(livro);
		
		cotuba.executa(parametros, System.out::println, mdsDoBD);

		return parametros.getArquivoDeSaida();
	}

}
