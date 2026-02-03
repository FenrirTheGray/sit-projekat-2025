package rs.ac.singidunum.servelogic.utility.devDataInit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import rs.ac.singidunum.servelogic.model.Modifier;
import rs.ac.singidunum.servelogic.model.ModifierType;
import rs.ac.singidunum.servelogic.repository.IModifierRepository;
import rs.ac.singidunum.servelogic.repository.ModifierTypeRepository;

@Component
@Order(2)
@Profile("devDataInit")
public class ModifierDataInit extends AbstractDataInit<IModifierRepository, Modifier> {

	@Value("${app.devDataInit.modifiers:10}")
	private int itemCount;
	
	@Autowired
	private ModifierTypeRepository tRepo;

	@Override
	protected void dataInit() {

        List<Modifier> modifiers = new ArrayList<>();
        List<ModifierType> types = tRepo.findAll();

        try {
        	for (int i = 1; i <= itemCount; i++) {
        		modifiers.add(new Modifier(
        				null, null,
        				"Modifier m" + i,
        				"Description for Modifier m" + i,
        				i * 10.0,
        				true,
        				types.stream().skip(ThreadLocalRandom.current().nextInt(0, types.size()-1)).limit(1).findFirst().get()
        				));
        	}
		} catch (Exception e) {
			System.err.println("Modifier Data initialization failed");
			e.printStackTrace();
		}

        repo.saveAll(modifiers);
	}

}
