package rs.ac.singidunum.servelogic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.singidunum.servelogic.dto.file.CategoryXMLWrapper;
import rs.ac.singidunum.servelogic.dto.response.CategoryResponseDTO;
import rs.ac.singidunum.servelogic.model.Category;
import rs.ac.singidunum.servelogic.service.CategoryService;

@RestController
@RequestMapping("/files/categories")
public class CategoryFileController extends AbstractFileController<Category, CategoryResponseDTO, CategoryXMLWrapper, CategoryService> {
	@Override
	protected String getFilename() {
		return "categories";
	}
}
