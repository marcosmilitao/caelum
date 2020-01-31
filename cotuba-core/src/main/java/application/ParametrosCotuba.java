package application;

import java.nio.file.Path;

import domain.FormatoEbook;

public interface ParametrosCotuba {

	FormatoEbook getFormato();

	Path getArquivoDeSaida();

}