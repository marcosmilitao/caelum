package md;

import java.util.ArrayList;
import java.util.List;

import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import application.RenderizadorMDParaHTML;
import application.RepositorioDeMDs;
import domain.Capitulo;
import domain.builder.CapituloBuilder;
import tema.AplicadorTema;

public class RenderizadorMDParaHTMLComCommonMark implements RenderizadorMDParaHTML {

	/* (non-Javadoc)
	 * @see md.RenderizadorMDParaHTML#renderiza(java.nio.file.Path)
	 */
	@Override
	public List<Capitulo> renderiza(RepositorioDeMDs repositorioDeMDs) {
		
		List<Capitulo> capitulos = new ArrayList<>();
	
			for (String md : repositorioDeMDs.obtemMDsDosCapitulos()) {
					CapituloBuilder capituloBuilder = new CapituloBuilder();
					
					Parser parser = Parser.builder().build();
					Node document = null;
					try {
						document = parser.parse(md);
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
						throw new RuntimeException("Erro ao fazer parse do markdown ", ex);
					}

					try {
						HtmlRenderer renderer = HtmlRenderer.builder().build();
						String html = renderer.render(document);
						
						//capitulo.setConteudoHTML(html);
						
						
						AplicadorTema tema = new AplicadorTema();
						// tema.aplica(capitulo);
						String htmlComTemas = tema.aplica(html);
						
						capituloBuilder.comConteudoHTML(htmlComTemas);
						
						Capitulo capitulo = capituloBuilder.constroi();
						
						capitulos.add(capitulo);


					} catch (Exception ex) {
						throw new RuntimeException("Erro ao renderizar MD para HTML " , ex);
					}
			}
		
		return capitulos;
		
	}

}
