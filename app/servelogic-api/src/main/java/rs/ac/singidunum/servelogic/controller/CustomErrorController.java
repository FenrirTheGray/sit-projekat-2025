package rs.ac.singidunum.servelogic.controller;

import java.util.Optional;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {
	
	@RequestMapping("/error")
	public ResponseEntity<Integer> handleError(HttpServletRequest request) {
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());

	        if(statusCode == HttpStatus.BAD_REQUEST.value()) {
	        	return ResponseEntity.badRequest().build();
	        } else if(statusCode == HttpStatus.NOT_FOUND.value()) {
	            return ResponseEntity.notFound().build();
	        } else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	            return ResponseEntity.internalServerError().build();
	        } else if(statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
	        	return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
	        }
	        return ResponseEntity.of(Optional.empty());
	    }
		return null;
	}
	
}
