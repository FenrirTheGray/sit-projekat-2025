package rs.ac.singidunum.servelogic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.singidunum.servelogic.dto.create.ArticleCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.response.ArticleResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.ArticleUpdateRequestDTO;
import rs.ac.singidunum.servelogic.model.Article;
import rs.ac.singidunum.servelogic.service.ArticleService;

@RestController
@RequestMapping(value={"/api/articles"})
public class ArticleDataController extends AbstractDataController<Article, ArticleResponseDTO, ArticleCreateRequestDTO, ArticleUpdateRequestDTO, ArticleService> {
	
}
