package rs.ac.singidunum.servelogic.model;

import com.arangodb.springframework.annotation.Ref;

import java.util.ArrayList;
import java.util.List;

public class ComboChoice extends Choice {

    @Ref
    private Combo combo;
    private List<ArticleChoice> articleChoiceList = new ArrayList<>();

    public ComboChoice(){}

    public ComboChoice(Combo combo, int amount){
        super(amount);
        this.combo = combo;
    }

    public ComboChoice(Combo combo, int amount, List<ArticleChoice> articleChoiceList){
        this(combo, amount);
        this.articleChoiceList = articleChoiceList;
    }

    @Override
    public void setProduct(Product c) {
        if(!c.getClass().getSimpleName().equals("Combo")) throw new ClassCastException();

        this.combo = (Combo) c;
    }
    @Override
    public Combo getProduct(){
        return this.combo;
    }

    public List<ArticleChoice> getArticleChoiceList() {
        return articleChoiceList;
    }
    public void setArticleChoiceList(List<ArticleChoice> articleChoiceList) {
        this.articleChoiceList = articleChoiceList;
    }
    public void addArticleChoice(ArticleChoice choice) {
        this.articleChoiceList.add(choice);
    }
    public void removeArticleChoice(ArticleChoice choice) {
        this.articleChoiceList.remove(choice);
    }
    public void removeModifierByIndex(int i) {
        try {
            this.articleChoiceList.remove(i);
        } catch (Exception e) {}
    }

    @Override
    public double getTotal(){
        double totalPrice = combo.getBasePrice();

        for(ArticleChoice choice : articleChoiceList) totalPrice += choice.getModifierPrice();

        return totalPrice * this.getAmount();
    }

}
