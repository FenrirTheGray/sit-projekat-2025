package rs.ac.singidunum.servelogic.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.servelogic.model.Article;
import rs.ac.singidunum.servelogic.repository.IArticleRepository;

@Service
public class ArticleService {
	private final IArticleRepository repo;

	public ArticleService(IArticleRepository repo) {
		super();
		this.repo = repo;
	}
	
	public List<Article> findAll() {
		List<Article> list = StreamSupport
				.stream(repo.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return list;
	}
	
	public Optional<Article> findByKey(String key) {
		Optional<Article> item = null;
		item = repo.findById(key);
		return item;
	}
	
	public Article save(Article item) {
		return repo.save(item);
	}
	
}
