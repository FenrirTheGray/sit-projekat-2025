package rs.ac.singidunum.servelogic.utility.devDataInit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import rs.ac.singidunum.servelogic.model.Modifier;
import rs.ac.singidunum.servelogic.model.ModifierType;
import rs.ac.singidunum.servelogic.repository.IModifierRepository;
import rs.ac.singidunum.servelogic.repository.ModifierTypeRepository;

@Component
@Order(2)
public class ModifierDataInit implements ApplicationRunner {

	@Autowired
	private IModifierRepository repo;
	@Autowired
	private ModifierTypeRepository tRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (repo.count() > 0) {
            return;
        }

        List<Modifier> modifiers = new ArrayList<>();
        List<ModifierType> types = tRepo.findAll();

        try {
        	for (int i = 1; i <= 15; i++) {
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
