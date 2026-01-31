package rs.ac.singidunum.servelogic.model;

import java.util.List;

public class ArticleChoice extends Choice {
    private Article article;
    private List<Modifier>  modifierList;

    public double getModifierPrice(){
        double modifierPrice = 0.0;

        for(Modifier m : modifierList) modifierPrice += m.getPrice();

        return modifierPrice;
    }
    @Override
    public double getTotal(){
        return article.getBasePrice() + getModifierPrice();
    }
}
