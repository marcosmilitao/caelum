package md;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import application.RenderizadorMDParaHTML;
import domain.Capitulo;
import domain.builder.CapituloBuilder;
import tema.AplicadorTema;

public class RenderizadorMDParaHTMLComCommonMark implements RenderizadorMDParaHTML {

	/* (non-Javadoc)
	 * @see md.RenderizadorMDParaHTML#renderiza(java.nio.file.Path)
	 */
	@Override
	public List<Capitulo> renderiza(Path diretorioDosMD) {
		
		List<Capitulo> capitulos = new ArrayList<>();
		
		PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.md");
		try (Stream<Path> arquivosMD = Files.list(diretorioDosMD)) {
			arquivosMD
				.filter(matcher::matches)
				.sorted()
				.forEach(arquivoMD -> {
					CapituloBuilder capituloBuilder = new CapituloBuilder();
					
					Parser parser = Parser.builder().build();
					Node document = null;
					try {
						document = parser.parseReader(Files.newBufferedReader(arquivoMD));
						document.accept(new AbstractVisitor() {
							@Override
							public void visit(Heading heading) {
								if (heading.getLevel() == 1) {
									// capítulo
									String tituloDoCapitulo = ((Text) heading.getFirstChild()).getLiteral();									
									//capitulo.setTitulo(tituloDoCapitulo);
									capituloBuilder.comTitulo(tituloDoCapitulo);
									// TODO: usar título do capítulo
									
								} else if (heading.getLevel() == 2) {
									// seção
								} else if (heading.getLevel() == 3) {
									// título
								}
							}

						});
					} catch (Exception ex) {
						throw new RuntimeException("Erro ao fazer parse do arquivo " + arquivoMD, ex);
					}

					try {
						HtmlRenderer renderer = HtmlRenderer.builder().build();
						String html = renderer.render(document);
						
						//capitulo.setConteudoHTML(html);
						
						
						AplicadorTema tema = new AplicadorTema();
						// tema.aplica(capitulo);
						String htmlComTemas = tema.aplica(html);
						
						capituloBuilder.comConteudoHTML(html);
						
						Capitulo capitulo = capituloBuilder.constroi();
						
						capitulos.add(capitulo);


					} catch (Exception ex) {
						throw new RuntimeException("Erro ao renderizar para HTML o arquivo " + arquivoMD, ex);
					}
				});
		} catch (IOException ex) {
			throw new RuntimeException(
					"Erro tentando encontrar arquivos .md em " + diretorioDosMD.toAbsolutePath(), ex);
		}
		
		return capitulos;
		
	}

}
