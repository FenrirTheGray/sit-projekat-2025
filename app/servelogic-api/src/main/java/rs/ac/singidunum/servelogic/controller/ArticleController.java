package rs.ac.singidunum.servelogic.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import rs.ac.singidunum.servelogic.dto.create.ArticleCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.file.ArticleFileDTO;
import rs.ac.singidunum.servelogic.dto.file.ArticleXMLWrapper;
import rs.ac.singidunum.servelogic.dto.response.ArticleResponseDTO;
import rs.ac.singidunum.servelogic.dto.response.ResponseWithMessageDTO;
import rs.ac.singidunum.servelogic.dto.update.ArticleUpdateRequestDTO;
import rs.ac.singidunum.servelogic.service.ArticleService;

@RestController
@RequestMapping(value={"/api"})
public class ArticleController {
	
	@Autowired
	private ArticleService service;

	@GetMapping("/articles")
	public List<ArticleResponseDTO> findAll() {
		return service.findAll();
	}
	@GetMapping("/articles/{key}")
	public ResponseEntity<ArticleResponseDTO> findByKey(@PathVariable("key") String key) {
		return service.findByKey(key).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	@PostMapping("/articles")
	public ResponseEntity<ArticleResponseDTO> create(@RequestBody ArticleCreateRequestDTO item) {
		return service.create(item).map(created -> ResponseEntity.status(HttpStatus.CREATED).body(created))
				.orElse(ResponseEntity.badRequest().build());
	}
	@PutMapping("/articles/{key}")
	public ResponseEntity<ArticleResponseDTO> update(@PathVariable String key, @RequestBody ArticleUpdateRequestDTO item) {
		if (item.getKey() != null && item.getKey().equals(key)) {
			return service.update(item).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
		}
		return ResponseEntity.badRequest().build();
	}
	@DeleteMapping("/articles/{key}")
	public ResponseEntity<Void> delete(@PathVariable String key) {
		service.deleteByKey(key);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/files/articles/{format}")
	public ResponseEntity<?> exportArticles(@PathVariable String format) {
		List<ArticleFileDTO> data = service.findAllExport();

		return switch (format.toLowerCase()) {
		case "json" -> ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=articles.json")
				.body(data);

		case "xml" -> ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=articles.xml")
				.contentType(MediaType.APPLICATION_XML).body(new ArticleXMLWrapper<>(data));

		default -> ResponseEntity.badRequest().body("Unsupported format");
		};
	}
	
	@PostMapping(value = "/files/articles/{format}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResponseWithMessageDTO> importArticles(@PathVariable String format,@RequestParam("file") MultipartFile file) {

	    if (file.isEmpty()) {
	        return ResponseEntity.badRequest().body(new ResponseWithMessageDTO("Please upload a file."));
	    }

	    try {
	        byte[] bytes = file.getBytes();
	        service.importData(bytes, format);
	        
	        return ResponseEntity.ok(new ResponseWithMessageDTO("Import successful!"));
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
	                             .body(new ResponseWithMessageDTO("Validation Error: " + e.getMessage()));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(new ResponseWithMessageDTO("Import failed: " + e.getMessage()));
	    }
	}
	
}
