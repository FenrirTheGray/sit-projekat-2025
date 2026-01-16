package rs.ac.singidunum.servelogic.utility.devDataInit;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import rs.ac.singidunum.servelogic.model.ModifierType;
import rs.ac.singidunum.servelogic.repository.ModifierTypeRepository;

@Component
@Order(1)
public class ModifierTypeDataInit {
	
	@Autowired
	private ModifierTypeRepository repo;
	
	@PostConstruct
	public void init() {
		List<ModifierType> list = repo.findAll();
		
		if (list.size() == 0) {
			repo.save(new ModifierType(null, "size", true));
			repo.save(new ModifierType(null, "topping", true));
			repo.save(new ModifierType(null, "salad", true));
		}
    }
	
}
