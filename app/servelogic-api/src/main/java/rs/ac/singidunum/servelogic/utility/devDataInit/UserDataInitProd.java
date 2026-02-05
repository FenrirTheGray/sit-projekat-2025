package rs.ac.singidunum.servelogic.utility.devDataInit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import rs.ac.singidunum.servelogic.model.User;
import rs.ac.singidunum.servelogic.repository.IUserRepository;
import rs.ac.singidunum.servelogic.service.UserService;

@Component
@Order(4)
@Profile("!devDataInit")
public class UserDataInitProd extends AbstractDataInit<IUserRepository, User> {

    @Autowired
    protected UserService service;

    @Value("${app.root-user.email}")
    private String rootEmail;

    @Value("${app.root-user.password}")
    private String rootPassword;

    @Override
    public void dataInit() {
        service.create(new User(null, null, rootEmail, rootPassword, "ADMIN"));
        initMore();
    }

    protected void initMore() {}
}
