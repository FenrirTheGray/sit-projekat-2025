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

        List<rs.ac.singidunum.servelogic.model.Order> orders = new ArrayList<>();

        for(int j = 0; j < 10; j++){
            orders.add(new rs.ac.singidunum.servelogic.model.Order(
                String.format("order/%d", j+1), String.format("%d", j+1),
                users.get(ThreadLocalRandom.current().nextInt(users.size())),
                new Date(),
                OrderStatus.CREATED,
                generateRandomChoices(products, 3)
            ));
        }

        repo.saveAll(orders);
    }

    private List<Choice> generateRandomChoices(List<Product> products, int number){
        ArrayList<Choice> choices = new ArrayList<>();

        Collections.shuffle(products);

        products.stream().skip(ThreadLocalRandom.current().nextInt(0, products.size()- (number + 1))).limit(number).forEach(product ->{
            if(product.getClass().getSimpleName().equals("Article")){
                choices.add(generateRandomArticleChoice((Article) product));
            }
            else{
                choices.add(generateRandomComboChoice((Combo) product));
            }
        });

        return choices;
    }

    private ArticleChoice generateRandomArticleChoice(Article a){

        int mSize = a.getModifiers().size();
        List<Modifier> modifiers = new ArrayList<>();
        if(mSize > 0){
            modifiers = List.of(a.getModifiers().get(ThreadLocalRandom.current().nextInt(0, mSize)));
        }

        return new ArticleChoice(a, ThreadLocalRandom.current().nextInt(1, 10), modifiers);
    }

    private ComboChoice generateRandomComboChoice(Combo c){


        int size = 0;
        List<ArticleChoice> articleChoices = new ArrayList<>();

        size = c.getMainSelection().size();
        articleChoices.add(generateRandomArticleChoice(c.getMainSelection().get(ThreadLocalRandom.current().nextInt(0, size))));

        size = c.getSideSelection().size();
        articleChoices.add(generateRandomArticleChoice(c.getSideSelection().get(ThreadLocalRandom.current().nextInt(0, size))));

        size = c.getDrinkSelection().size();
        articleChoices.add(generateRandomArticleChoice(c.getDrinkSelection().get(ThreadLocalRandom.current().nextInt(0, size))));

        return new ComboChoice(c, ThreadLocalRandom.current().nextInt(1, 10), articleChoices);
    }

}
