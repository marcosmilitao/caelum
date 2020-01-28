package plugin;

import java.util.ServiceLoader;

import plugin.Ebook;

public interface AoFinalizarGeracao {
	void aposGeracao(Ebook ebook);
	
	static void gerou(Ebook ebook) {
		ServiceLoader<AoFinalizarGeracao> loader = ServiceLoader.load(AoFinalizarGeracao.class);
		
		for(AoFinalizarGeracao plugin : loader) {
			plugin.aposGeracao(ebook);
		}
	}
}
