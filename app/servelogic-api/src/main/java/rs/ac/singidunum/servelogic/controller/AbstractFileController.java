package rs.ac.singidunum.servelogic.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.singidunum.servelogic.dto.file.FileXMLWrapper;
import rs.ac.singidunum.servelogic.dto.response.ResponseWithMessageDTO;
import rs.ac.singidunum.servelogic.service.AbstractService;

public abstract class AbstractFileController<T, F, W extends FileXMLWrapper<F>, S extends AbstractService<T, ?, ?, ?, F, W>> {
	
	@Autowired
	private S service;
	
	protected abstract String getFilename();
	
	@GetMapping("/{format}")
	public ResponseEntity<?> exportData(@PathVariable String format) {
		List<F> data = service.findAllExport();

		return switch (format.toLowerCase()) {
		case "json" -> ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + getFilename() + ".json")
				.body(data);

		case "xml" -> ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + getFilename() + ".xml")
				.contentType(MediaType.APPLICATION_XML).body(service.wrapper(data));

		default -> ResponseEntity.badRequest().body(new ResponseWithMessageDTO("Unsupported format"));
		};
	}
	@PostMapping(value = "/{format}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResponseWithMessageDTO> importData(@PathVariable String format,@RequestParam("file") MultipartFile file) {

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
