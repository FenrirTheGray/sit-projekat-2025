package rs.ac.singidunum.servelogic.utility.devDataInit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import rs.ac.singidunum.servelogic.model.*;
import rs.ac.singidunum.servelogic.repository.IOrderRepository;
import rs.ac.singidunum.servelogic.service.ArticleService;
import rs.ac.singidunum.servelogic.service.ComboService;
import rs.ac.singidunum.servelogic.service.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Order(5)
public class OrderDataInit  implements ApplicationRunner {

    @Autowired
    private IOrderRepository repo;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ComboService comboService;
    @Autowired
    private UserService userService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(repo.count() > 0) return;

        List<User> users = userService.findAllInit();
        List<Combo> combos = comboService.findAllInit();
        List<Article> articles = articleService.findAllInit();
        List<Product> products = new ArrayList<>();

        products.addAll(combos);
        products.addAll(articles);

        Collections.shuffle(products);

        List<rs.ac.singidunum.servelogic.model.Order> orders = new ArrayList<>();

        orders.add(new rs.ac.singidunum.servelogic.model.Order(
                "order/1", "1",
                users.get(ThreadLocalRandom.current().nextInt(users.size())),
                new Date(),
                OrderStatus.CREATED,
                new ArrayList<>()
        ));
    }
}
