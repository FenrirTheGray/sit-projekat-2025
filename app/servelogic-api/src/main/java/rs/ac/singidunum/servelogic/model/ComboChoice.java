package rs.ac.singidunum.servelogic.model;

import java.util.List;

public class ComboChoice extends Choice {
    private Combo combo;
    private List<ArticleChoice> articleChoiceList;

    @Override
    public double getTotal(){
        double totalPrice = combo.getBasePrice();

        for(ArticleChoice choice : articleChoiceList) totalPrice += choice.getModifierPrice();

        return totalPrice;
    }

}
