package rs.ac.singidunum.servelogic.utility;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.servelogic.model.Article;
import rs.ac.singidunum.servelogic.model.Category;
import rs.ac.singidunum.servelogic.model.Modifier;
import rs.ac.singidunum.servelogic.repository.CategoryRepository;
import rs.ac.singidunum.servelogic.repository.ModifierTypeRepository;

@Service
public class ArangoFusekiReferenceService {

	@Autowired
	private ModifierTypeRepository modifierTypeRepo;
	@Autowired
	private CategoryRepository categoryRepo;

	public Article populate(Article item) {
		if (item == null || item.getModifiers() == null) {
			return null;
		}
		item.getModifiers().forEach(mod -> {
			mod = this.populate(mod);
		});
		
		if (item.getCategoryId() != null) {
			Optional<Category> cat = categoryRepo.findById(item.getCategoryId());
			if (cat.isPresent()) {
				item.setCategory(cat.get());
			}
		}

		return item;
	}
	
	public Modifier populate(Modifier item) {
		if (item == null) {
			return null;
		}
		if (item.getTypeId() != null) {
			modifierTypeRepo.findById(item.getTypeId()).ifPresent(item::setType);
		}
		return item;
	}
	
	public List<Article> populateAllArticles(List<Article> items) {
		items.forEach(item -> {
			item = this.populate(item);
		});
		return (List<Article>) items;
	}
	
	public List<Modifier> populateAllModifiers(List<Modifier> items) {
		items.forEach(item -> {
			item = this.populate(item);
		});
		return (List<Modifier>) items;
	}

}
