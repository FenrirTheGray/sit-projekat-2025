package rs.ac.singidunum.servelogic.utility.devDataInit;

import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import rs.ac.singidunum.servelogic.model.User;

@Component
@Order(4)
@Profile("devDataInit")
public class UserDataInit extends UserDataInitProd {
	
	@Value("${app.devDataInit.users:5}")
	private int itemCount;

    
    protected void initMore() {
    	for (int i = 1; i <= itemCount; i++) {
        	service.create(new User(null, null, "userEmail" + i + "@sl.com", "password", ThreadLocalRandom.current().nextBoolean() ? "ADMIN" : "USER"));
        }
    }
}
