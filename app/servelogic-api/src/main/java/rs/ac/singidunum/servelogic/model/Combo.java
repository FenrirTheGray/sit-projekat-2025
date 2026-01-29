package rs.ac.singidunum.servelogic.model;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import java.util.ArrayList;
import java.util.List;

@Document("combo")
public class Combo extends Product {

    @Ref
    private List<Article> mainSelection = new ArrayList<>();
    @Ref
    private List<Article> sideSelection = new ArrayList<>();
    @Ref
    private List<Article> drinkSelection = new ArrayList<>();


    public Combo(String id, String key, String name, String imageUrl, double basePrice, boolean active) {
        super(id, key, name, imageUrl, basePrice, active);
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
}
