package rs.ac.singidunum.servelogic.model;

import com.arangodb.springframework.annotation.Ref;

import java.util.ArrayList;
import java.util.List;

public class ArticleChoice extends Choice {

    @Ref
    private Article article;
    @Ref
    private List<Modifier>  modifierList = new ArrayList<>();

    public ArticleChoice(){}

    public ArticleChoice(Article a, int amount, List<Modifier> modifierList){
        this(a, amount);
        this.modifierList = modifierList;
    }

    public ArticleChoice(Article a, int amount){
        super(amount);
        this.article = a;
    }

    public double getModifierPrice(){
        double modifierPrice = 0.0;

        for(Modifier m : modifierList) modifierPrice += m.getPrice();

        return modifierPrice;
    }

    @Override
    public void setProduct(Product a) {
        if(!a.getClass().getSimpleName().equals("Article")) throw new ClassCastException();

        this.article = (Article) a;
    }
    @Override
    public Article getProduct(){
        return this.article;
    }

    public List<Modifier> getModifiers() {
        return modifierList;
    }
    public void setModifiers(List<Modifier> modifiers) {
        this.modifierList = modifiers;
    }
    public void addModifier(Modifier modifier) {
        this.modifierList.add(modifier);
    }
    public void removeModifier(Modifier modifier) {
        this.modifierList.remove(modifier);
    }
    public void removeModifierByIndex(int i) {
        try {
            this.modifierList.remove(i);
        } catch (Exception e) {}
    }

    @Override
    public double getTotal(){
        return (article.getBasePrice() + getModifierPrice()) * this.getAmount();
    }

}
