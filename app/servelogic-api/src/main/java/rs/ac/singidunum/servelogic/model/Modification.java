package rs.ac.singidunum.servelogic.model;

import com.arangodb.springframework.annotation.Document;

@Document("modification")
public class Modification extends AbstractEntity {
	
	private String name;
	private String description;
	private double price;
	private boolean active;
	
	protected Modification() {
		super();
	}
	
	public Modification(String id, String key, String name, String description, double price, boolean active) {
		super(id, key);
		this.name = name;
		this.description = description;
		this.price = price;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
