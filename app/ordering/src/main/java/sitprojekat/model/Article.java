package sitprojekat.model;

import java.util.List;

public class Article extends AbstractEntity {

	private String name;
	private String description;
	private String imageUrl; 
	private double basePrice;
	private boolean active;
	private Category category;
	private List<Modifier> modifiers;

	public Article() {
		super();
	}

	public Article(String id, String name, String description, double basePrice, boolean active, Category category,
			List<Modifier> modifiers) {
		super(id);
		this.name = name;
		this.description = description;
		this.basePrice = basePrice;
		this.active = active;
		this.category = category;
		this.modifiers = modifiers;
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

	public List<Modifier> getModifiers() {
		return modifiers;
	}

	public void setModifiers(List<Modifier> modifiers) {
		this.modifiers = modifiers;
	}

	
	

}
