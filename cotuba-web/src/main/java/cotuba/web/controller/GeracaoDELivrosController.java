package cotuba.web.controller;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cotuba.web.SpringFilesUtils;
import cotuba.web.application.GeracaoDeLivros;
import domain.FormatoEbook;

@Controller
public class GeracaoDELivrosController {
	
	private final GeracaoDeLivros geracao;
	
	public GeracaoDELivrosController(GeracaoDeLivros geracao) {
		this.geracao = geracao;
	}

	@GetMapping("/livros/{id}/pdf")
	public ResponseEntity<ByteArrayResource> gerarPDF(@PathVariable("id") Long id, Model model){
		Path pdf = geracao.geraLivro(id, FormatoEbook.PDF);
		
		return SpringFilesUtils.montaResponseOArquivo(pdf, "application/pdf");
	}
	
	@GetMapping("/livros/{id}/epub")
	public ResponseEntity<ByteArrayResource> gerarEPUB(@PathVariable("id") Long id, Model model){
		Path epub = geracao.geraLivro(id, FormatoEbook.EPUB);
		
		return SpringFilesUtils.montaResponseOArquivo(epub, "application/epub+zip");
	}
}
