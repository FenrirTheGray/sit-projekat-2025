package rs.ac.singidunum.servelogic.utility.devDataInit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import rs.ac.singidunum.servelogic.model.Category;
import rs.ac.singidunum.servelogic.repository.CategoryRepository;

@Component
@Order(1)
@Profile("devDataInit")
public class CategoryDataInit extends AbstractDataInit<CategoryRepository, Category> {
	
	@Value("${app.devDataInit.categories:3}")
	private int itemCount;
	
	@Override
	protected void dataInit() {
		for (int i = 1; i <= itemCount; i++) {
			repo.save(new Category(null, "category " + i, "Category " + i + " description", true));
		}
	}
}
