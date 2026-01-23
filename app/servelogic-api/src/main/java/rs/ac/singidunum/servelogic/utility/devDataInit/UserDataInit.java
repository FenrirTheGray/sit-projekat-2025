package rs.ac.singidunum.servelogic.utility.devDataInit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import rs.ac.singidunum.servelogic.model.User;
import rs.ac.singidunum.servelogic.repository.IUserRepository;
import rs.ac.singidunum.servelogic.service.UserService;

@Component
@Order(4)
public class UserDataInit implements ApplicationRunner {
    @Autowired
    private IUserRepository repo;
    @Autowired
    private UserService service;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(repo.count() > 0) return;

        User dusan =  new User("user/1", "1", "dusan", "Dusan123", "ADMIN");
        User gojko =  new User("user/2", "2", "gojko", "Gojko123", "ADMIN");
        User boris =  new User("user/3", "3", "boris", "Boris123", "ADMIN");
        User chola =  new User("user/4", "4", "chola", "Chola123", "ADMIN");
        User jovan =  new User("user/5", "5", "jovan", "Jovan123", "ADMIN");

        service.create(dusan);
        service.create(gojko);
        service.create(boris);
        service.create(chola);
        service.create(jovan);
    }
}
