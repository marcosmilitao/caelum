package plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public interface Tema {
	String cssDoTema();
	
	static List<String> listaTemas() {
		List<String> temas = new ArrayList<>();
		
		ServiceLoader<Tema> loader = ServiceLoader.load(Tema.class);
		for(Tema plugin : loader) {
			String css = plugin.cssDoTema();
			temas.add(css);
		}
		
		return temas;
	}
	
}
