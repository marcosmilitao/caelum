package plugin;

import java.nio.file.Path;
import java.util.List;

import domain.FormatoEbook;

public interface Ebook {
	FormatoEbook getFormato();
	Path getArquivoDeSaida();
	List<? extends Capitulo> getCapitulos();
}
