package pdf;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.property.AreaBreakType;

import domain.Capitulo;
import domain.Ebook;
import domain.FormatoEbook;
import plugin.GeradorEbook;

public class GeradorPDF implements GeradorEbook {

	/* (non-Javadoc)
	 * @see pdf.GeradorPDF#gera(domain.Ebook)
	 */
	@Override
	public void gera(Ebook ebook) {
		
		Path arquivoDeSaida = ebook.getArquivoDeSaida();
		int i = 0;
		
		try(PdfWriter writer = new PdfWriter(Files.newOutputStream(arquivoDeSaida));
				PdfDocument pdf = new PdfDocument(writer);
				Document pdfDocument = new Document(pdf)) {
			for (Capitulo capitulo : ebook.getCapitulos()) {
				i++;
				String html = capitulo.getConteudoHTML();
				List<IElement> convertToElements = HtmlConverter.convertToElements(html);
				for (IElement element : convertToElements) {
					pdfDocument.add((IBlockElement) element);
				}
				// TODO: não adicionar página depois do último capítulo
				if(i < ebook.getCapitulos().size()) {
					pdfDocument.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
				}
				
			}
			
			
			} catch (Exception ex) {
				throw new RuntimeException("Erro ao criar arquivo PDF: " + arquivoDeSaida.toAbsolutePath(), ex);
			}

		
	}

	@Override
	public FormatoEbook formato() {
		
		return FormatoEbook.PDF;
	}

}