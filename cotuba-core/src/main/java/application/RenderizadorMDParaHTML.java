package application;

import java.nio.file.Path;
import java.util.List;

import domain.Capitulo;
import md.RenderizadorMDParaHTMLComCommonMark;

public interface RenderizadorMDParaHTML {

	List<Capitulo> renderiza(Path diretorioDosMD);
	
	public static RenderizadorMDParaHTML cria() {
		return new RenderizadorMDParaHTMLComCommonMark();
	}

}