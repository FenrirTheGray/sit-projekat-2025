package rs.ac.singidunum.servelogic.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.singidunum.servelogic.model.Article;
import rs.ac.singidunum.servelogic.service.ArticleService;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

	private final ArticleService service;

	public ArticleController(ArticleService service) {
		super();
		this.service = service;
	}
	
	@GetMapping
	public List<Article> getAllArticles() {
		return service.findAll();
	}
	
}
