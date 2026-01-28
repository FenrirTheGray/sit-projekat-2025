package rs.ac.singidunum.servelogic.utility.devDataInit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import rs.ac.singidunum.servelogic.model.ModifierType;
import rs.ac.singidunum.servelogic.repository.ModifierTypeRepository;

@Component
@Order(1)
@Profile("devDataInit")
public class ModifierTypeDataInit extends AbstractDataInit<ModifierTypeRepository, ModifierType> {
	
	@Value("${app.devDataInit.modifierTypes:5}")
	private int itemCount;
	
	@Override
	public void dataInit() {
		for (int i = 1; i <= itemCount; i++) {
			repo.save(new ModifierType(null, "modifierType " + i, true));
		}
    }
	
}
