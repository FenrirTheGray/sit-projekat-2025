package rs.ac.singidunum.servelogic.dto.response;

import java.util.ArrayList;
import java.util.List;

import rs.ac.singidunum.servelogic.dto.AbstractArangoDTO;

public class ComboResponseDTO extends AbstractArangoDTO {
    private String name;
    private String description;
    private double basePrice;
    private boolean active;
    private String imageUrl;
    private List<ArticleResponseDTO> mainSelection = new ArrayList<>();
    private List<ArticleResponseDTO> sideSelection = new ArrayList<>();
    private List<ArticleResponseDTO> drinkSelection = new ArrayList<>();

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getBasePrice() {
        return basePrice;
    }
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public List<ArticleResponseDTO> getMainSelection() {
        return mainSelection;
    }
    public void setMainSelection(List<ArticleResponseDTO> mainSelection) {
        this.mainSelection = mainSelection;
    }
    public List<ArticleResponseDTO> getSideSelection() {
        return sideSelection;
    }
    public void setSideSelection(List<ArticleResponseDTO> sideSelection) {
        this.sideSelection = sideSelection;
    }
    public List<ArticleResponseDTO> getDrinkSelection() {
        return drinkSelection;
    }
    public void setDrinkSelection(List<ArticleResponseDTO> drinkSelection) {
        this.drinkSelection = drinkSelection;
    }
}
