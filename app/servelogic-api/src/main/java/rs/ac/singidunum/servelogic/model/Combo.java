package rs.ac.singidunum.servelogic.model;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import java.util.ArrayList;
import java.util.List;

@Document("combo")
public class Combo extends Product {


    private Article mainSelected;
    private Article sideSelected;
    private Article drinkSelected;

    @Ref
    private List<Article> mainSelection = new ArrayList<>();
    @Ref
    private List<Article> sideSelection = new ArrayList<>();
    @Ref
    private List<Article> drinkSelection = new ArrayList<>();


    public Combo(String id, String key, String name, String imageUrl, double basePrice, boolean active) {
        super(id, key, name, imageUrl, basePrice, active);
    }


    public Article getMainSelected() {
        return mainSelected;
    }

    public void setMainSelected(Article mainSelected) {
        this.mainSelected = mainSelected;
    }

    public Article getSideSelected() {
        return sideSelected;
    }

    public void setSideSelected(Article sideSelected) {
        this.sideSelected = sideSelected;
    }

    public Article getDrinkSelected() {
        return drinkSelected;
    }

    public void setDrinkSelected(Article drinkSelected) {
        this.drinkSelected = drinkSelected;
    }

    public List<Article> getMainSelection() {
        return mainSelection;
    }
    public void setMainSelection(List<Article> mainSelection) {
        this.mainSelection = mainSelection;
    }
    public void addMainSelection(Article article) {
        this.mainSelection.add(article);
    }
    public void removeMainSelection(Article article) {
        this.mainSelection.remove(article);
    }
    public void removeMainSelectionByIndex(int i) {
        try {
            this.mainSelection.remove(i);
        } catch (Exception e) {}
    }

    public List<Article> getSideSelection() {
        return sideSelection;
    }
    public void setSideSelection(List<Article> sideSelection) {
        this.sideSelection = sideSelection;
    }
    public void addSideSelection(Article article) {
        this.sideSelection.add(article);
    }
    public void removeSideSelection(Article article) {
        this.sideSelection.remove(article);
    }
    public void removeSideSelectionByIndex(int i) {
        try {
            this.sideSelection.remove(i);
        } catch (Exception e) {}
    }

    public List<Article> getDrinkSelection() {
        return drinkSelection;
    }
    public void setDrinkSelection(List<Article> drinkSelection) {
        this.drinkSelection = drinkSelection;
    }
    public void addDrinkSelection(Article article) {
        this.drinkSelection.add(article);
    }
    public void removeDrinkSelection(Article article) {
        this.drinkSelection.remove(article);
    }
    public void removeDrinkSelectionByIndex(int i) {
        try {
            this.drinkSelection.remove(i);
        } catch (Exception e) {}
    }

    @Override
    public double calculatePrice(){
        double total = getBasePrice();

        total += mainSelected.calculatePrice();
        total += sideSelected.calculatePrice();
        total += drinkSelected.calculatePrice();

        return total;
    }
}
