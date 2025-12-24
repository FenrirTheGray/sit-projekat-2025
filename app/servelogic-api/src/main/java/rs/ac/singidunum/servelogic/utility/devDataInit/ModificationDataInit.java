package rs.ac.singidunum.servelogic.utility.devDataInit;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import rs.ac.singidunum.servelogic.model.Modification;
import rs.ac.singidunum.servelogic.repository.IModificationRepository;

@Component
@Order(1)
public class ModificationDataInit implements ApplicationRunner {

	private final IModificationRepository repo;
	
	public ModificationDataInit(IModificationRepository repo) {
		super();
		this.repo = repo;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (repo.count() > 0) {
            return;
        }

        List<Modification> articles = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            articles.add(new Modification(
                null, null,
                "Modification " + i,
                "Description for Modification " + i,
                i * 10.0,
                true
            ));
        }

        repo.saveAll(articles);
	}

}
