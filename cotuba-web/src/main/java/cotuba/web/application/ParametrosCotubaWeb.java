package cotuba.web.application;

import java.nio.file.Files;
import java.nio.file.Path;

import application.ParametrosCotuba;
import domain.FormatoEbook;

public class ParametrosCotubaWeb implements ParametrosCotuba {
	
	private FormatoEbook formato;
	private Path arquivoDeSaida;
	
	public ParametrosCotubaWeb(FormatoEbook formato) {
		this.formato = formato;
	}

	@Override
	public FormatoEbook getFormato() {
		return formato;
	}

	@Override
	public Path getArquivoDeSaida() {
		if(arquivoDeSaida != null) {
			return arquivoDeSaida;
		}
		
		try {
			Path diretorio = Files.createTempDirectory("ebooks");
			String nomeDoArquivoDeSainda = "book." + formato.name().toLowerCase();
			this.arquivoDeSaida = diretorio.resolve(nomeDoArquivoDeSainda);
			return arquivoDeSaida;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
