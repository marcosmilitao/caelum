package application;

import java.nio.file.Path;

import domain.FormatoEbook;

public interface ParametrosCotuba {

	Path getDiretorioDosMD();

	FormatoEbook getFormato();

	Path getArquivoDeSaida();

}