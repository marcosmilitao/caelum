package cli;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import application.RepositorioDeMDs;

public class MDsDoDiretorio implements RepositorioDeMDs{
	
	private Path diretorioDosMD;
	
	public MDsDoDiretorio(Path diretorioDosMD) {
		this.diretorioDosMD = diretorioDosMD;
	}

	@Override
	public List<String> obtemMDsDosCapitulos() {
		List<String> mds = new ArrayList<>();
		
		PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.md");
		
		try (Stream<Path> arquivosMD = Files.list(diretorioDosMD)){
			arquivosMD.filter(matcher::matches).sorted().forEach(arquivoMD -> {
				try {
					String md = new String (Files.readAllBytes(arquivoMD));
					mds.add(md);
				} catch (Exception e) {
					throw new RuntimeException("Erro ao ler arquivo " + arquivoMD, e);
				}
			});
			
		} catch (Exception e) {
			throw new RuntimeException("Erro tentando encontrar arquivos .md em " + diretorioDosMD.toAbsolutePath(),e);
		}
		return null;
	}

}
