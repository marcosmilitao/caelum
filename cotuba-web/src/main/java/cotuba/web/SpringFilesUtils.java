package cotuba.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.ByteArrayResource;

public class SpringFilesUtils {
	
	public static ResponseEntity <ByteArrayResource> montaResponseOArquivo(Path path, String type) {
		try {
			byte[] data = Files.readAllBytes(path);
			ByteArrayResource resource = new ByteArrayResource(data);
			
			String filename = path.getFileName().toString();
			
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_TYPE, type);
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + filename);
			
			return ResponseEntity.ok().headers(headers).contentLength(data.length).body(resource); 
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
