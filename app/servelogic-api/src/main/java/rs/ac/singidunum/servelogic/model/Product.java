package rs.ac.singidunum.servelogic.model;

public abstract class Product extends AbstractArangoEntity {
    private String name;
    private String description;
    private double basePrice;
    private boolean active;
    private String imageUrl;

    public Product () {super();}

    public Product(String id, String key, String name, String description, String imageUrl, double basePrice, boolean active) {
        super(id, key);
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.basePrice = basePrice;
        this.active = active;
    }

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

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
}
