package rs.ac.singidunum.servelogic.utility.devDataInit;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import rs.ac.singidunum.servelogic.model.Category;
import rs.ac.singidunum.servelogic.repository.CategoryRepository;

@Component
@Order(1)
public class CategoryDataInit {
	
	@Autowired
	private CategoryRepository repo;
	
	@PostConstruct
	public void init() {
		List<Category> list = repo.findAll();
		
		if (list.size() == 0) {
			repo.save(new Category(null, "burger", "Burgers",true));
			repo.save(new Category(null, "pizza", "Pizzas",true));
			repo.save(new Category(null, "drink", "Drinks",true));
		}
    }
}
