package rs.ac.singidunum.servelogic.utility.devDataInit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import rs.ac.singidunum.servelogic.model.Article;
import rs.ac.singidunum.servelogic.model.Category;
import rs.ac.singidunum.servelogic.model.Modifier;
import rs.ac.singidunum.servelogic.repository.IArticleRepository;
import rs.ac.singidunum.servelogic.service.CategoryService;
import rs.ac.singidunum.servelogic.service.ModifierService;

@Component
@Order(3)
@Profile("devDataInit")
public class ArticleDataInit extends AbstractDataInit<IArticleRepository, Article> {
	
	@Value("${app.devDataInit.articles:25}")
	private int itemCount;

	@Autowired
	private ModifierService modifierService;
	@Autowired
	private CategoryService categoryService;

	@Override
	protected void dataInit() {
		List<Modifier> modifiers = modifierService.findAllInit();
		List<Category> categories = categoryService.findAllInit();
		List<Article> articles = new ArrayList<>();

		
		if (modifiers.isEmpty() || categories.isEmpty()) {
			System.out.println("Modifiers or Categories not initialized. Stopping Article initialization");
			return;
		}

		for (int i = 1; i <= itemCount; i++) {
			Article article = new Article(null, null, "Article a" + i, "Description for a" + i + " article.", "https://cdn.dribbble.com/userupload/22570626/file/original-379b4978ee41eeb352e0ddacbaa6df96.jpg?resize=512x384", i * ThreadLocalRandom.current().nextInt(100, 250), true);

			article.setModifiers(modifiers.stream().skip(ThreadLocalRandom.current().nextInt(0, modifiers.size()-3)).limit(2).toList());
			
			article.setCategory(categories.stream().skip(ThreadLocalRandom.current().nextInt(0, categories.size()-1)).limit(1).findFirst().get());

			articles.add(article);
		}

		repo.saveAll(articles);
	}

}
