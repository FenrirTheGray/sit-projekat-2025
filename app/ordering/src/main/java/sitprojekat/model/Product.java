package sitprojekat.model;

public abstract class Product extends AbstractEntity{

	
	private String name;
	private String description;
	private String imageUrl; 
	private double basePrice;
	private boolean active;
	private Category category;
	
	public Product() {}
	
	public Product(String id,String name, String description, String imageUrl, double basePrice, boolean active,Category category) {
		super(id);
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
		this.basePrice = basePrice;
		this.active = active;
		this.category=category;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}



	
	
	
	
}
