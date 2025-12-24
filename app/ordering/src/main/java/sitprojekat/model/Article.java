package sitprojekat.model;


public class Article extends AbstractEntity {

	private String name;
	private String description;
	private double basePrice;
	private boolean active;

	public Article(String id, String name, String description, double basePrice, boolean active) {
		super(id);
		this.name = name;
		this.description = description;
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
