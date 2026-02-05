package rs.ac.singidunum.servelogic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.singidunum.servelogic.dto.create.CategoryCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.response.CategoryResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.CategoryUpdateRequestDTO;
import rs.ac.singidunum.servelogic.model.Category;
import rs.ac.singidunum.servelogic.service.CategoryService;

@RestController
@RequestMapping(value = { "/api/categories" })
public class CategoryDataController extends AbstractDataController<Category, CategoryResponseDTO, CategoryCreateRequestDTO, CategoryUpdateRequestDTO, CategoryService> {
	
}
