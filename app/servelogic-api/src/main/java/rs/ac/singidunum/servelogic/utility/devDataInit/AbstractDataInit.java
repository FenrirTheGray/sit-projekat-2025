package rs.ac.singidunum.servelogic.utility.devDataInit;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.repository.CrudRepository;

public abstract class AbstractDataInit<R extends CrudRepository<T, String>, T> implements ApplicationRunner {
	
	@Autowired
	protected R repo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<T> list = new ArrayList<>();
		repo.findAll().forEach(list::add);
		if (list.size() > 0) {
			return;
		}
		
		dataInit();

	}
	
	protected abstract void dataInit();
}
