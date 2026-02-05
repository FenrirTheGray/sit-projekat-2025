package rs.ac.singidunum.servelogic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.singidunum.servelogic.dto.file.ArticleFileDTO;
import rs.ac.singidunum.servelogic.dto.file.ArticleXMLWrapper;
import rs.ac.singidunum.servelogic.model.Article;
import rs.ac.singidunum.servelogic.service.ArticleService;

@RestController
@RequestMapping("/files/articles")
public class ArticleFileController extends AbstractFileController<Article, ArticleFileDTO, ArticleXMLWrapper, ArticleService> {

	@Override
	protected String getFilename() {
		return "articles";
	}

}
