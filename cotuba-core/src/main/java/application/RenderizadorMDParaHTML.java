package application;

import java.util.List;

import domain.Capitulo;
import md.RenderizadorMDParaHTMLComCommonMark;

public interface RenderizadorMDParaHTML {

	List<Capitulo> renderiza(RepositorioDeMDs repositorioDeMDs);
	
	public static RenderizadorMDParaHTML cria() {
		return new RenderizadorMDParaHTMLComCommonMark();
	}

}