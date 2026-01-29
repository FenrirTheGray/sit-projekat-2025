package rs.ac.singidunum.servelogic.utility.devDataInit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import rs.ac.singidunum.servelogic.model.Article;
import rs.ac.singidunum.servelogic.model.Combo;
import rs.ac.singidunum.servelogic.repository.IComboRepository;
import rs.ac.singidunum.servelogic.service.ArticleService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Order(4)
public class ComboDataInit implements ApplicationRunner {

    @Autowired
    private IComboRepository repo;
    @Autowired
    private ArticleService articleService;

    @Override
    public void run(ApplicationArguments args) throws Exception{
        if(repo.count() > 0) return;

        List<Article> articles = articleService.findAllInit();

        List<Combo> combos = new ArrayList<>();

        combos.add(new Combo("combo/1", "1", "combo1", "https://images.pexels.com/photos/4676402/pexels-photo-4676402.jpeg",
                500, true));
        combos.add(new Combo("combo/2", "2", "combo2", "https://images.pexels.com/photos/4676402/pexels-photo-4676402.jpeg",
                520, true));
        combos.add(new Combo("combo/3", "3", "combo3", "https://images.pexels.com/photos/4676402/pexels-photo-4676402.jpeg",
                5430, true));
        combos.add(new Combo("combo/4", "4", "combo4", "https://images.pexels.com/photos/4676402/pexels-photo-4676402.jpeg",
                100, true));
        combos.add(new Combo("combo/5", "5", "combo5", "https://images.pexels.com/photos/4676402/pexels-photo-4676402.jpeg",
                4040, true));
        combos.add(new Combo("combo/6", "6", "combo6", "https://images.pexels.com/photos/4676402/pexels-photo-4676402.jpeg",
                67, true));

        for(Combo c : combos){
            c.setMainSelection(articles.stream().skip(ThreadLocalRandom.current().nextInt(0, articles.size()-4)).limit(3).toList());
            c.setSideSelection(articles.stream().skip(ThreadLocalRandom.current().nextInt(0, articles.size()-4)).limit(3).toList());
            c.setDrinkSelection(articles.stream().skip(ThreadLocalRandom.current().nextInt(0, articles.size()-4)).limit(3).toList());
        }

        repo.saveAll(combos);
    }

}
