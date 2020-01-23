package epub;

import java.io.IOException;
import java.nio.file.Files;

import application.GeradorEbook;
import domain.Capitulo;
import domain.Ebook;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.service.MediatypeService;

public class GeradorEPUB implements GeradorEbook {

	/* (non-Javadoc)
	 * @see epub.GeradorEPUB#gera(domain.Ebook)
	 */
	@Override
	public void gera(Ebook ebook) {
		
		Book epub = new Book();
		
		for(Capitulo capitulo : ebook.getCapitulos()) {
			
			String html = capitulo.getConteudoHTML();
			String tituloDoCapitulo = capitulo.getTitulo();
			
			epub.addSection(tituloDoCapitulo, new Resource(html.getBytes(), MediatypeService.XHTML));
		}

	
		EpubWriter epubWriter = new EpubWriter();

		try {
			epubWriter.write(epub, Files.newOutputStream(ebook.getArquivoDeSaida()));
		} catch (IOException ex) {
			throw new RuntimeException("Erro ao criar arquivo EPUB: " + ebook.getArquivoDeSaida().toAbsolutePath(), ex);
		}
	}

}
