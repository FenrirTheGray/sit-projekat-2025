package rs.ac.singidunum.servelogic.utility.devDataInit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import rs.ac.singidunum.servelogic.model.Article;
import rs.ac.singidunum.servelogic.model.Modification;
import rs.ac.singidunum.servelogic.repository.IArticleRepository;
import rs.ac.singidunum.servelogic.service.ModificationService;

@Component
@Order(2)
public class ArticleDataInit implements ApplicationRunner {

	private final IArticleRepository repo;
	private final ModificationService modificationsService;

	public ArticleDataInit(IArticleRepository repo, ModificationService modificationsService) {
		super();
		this.repo = repo;
		this.modificationsService = modificationsService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (repo.count() > 0) {
			return;
		}

		List<Modification> modifications = modificationsService.findAll();
		List<Article> articles = new ArrayList<>();

		if (modifications.isEmpty()) {
			System.out.println("Modifications not initialized. Stopping Article initialization");
			return;
		}

		for (int i = 1; i <= 10; i++) {
			Article article = new Article(null, null, "Article " + i, "Description for article " + i, i * 10.0, true);

			article.setModifications(modifications.stream().skip(ThreadLocalRandom.current().nextInt(0, modifications.size()-3)).limit(2).toList());

			articles.add(article);
		}

		repo.saveAll(articles);
	}

}
