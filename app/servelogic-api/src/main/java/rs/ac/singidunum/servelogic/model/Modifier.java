package rs.ac.singidunum.servelogic.model;

import org.springframework.data.annotation.Transient;
import com.arangodb.springframework.annotation.Document;

@Document("modifier")
public class Modifier extends AbstractArangoEntity {
	
	private String name;
	private String description;
	private double price;
	private boolean active;
	private String typeId;

	@Transient
	private ModifierType type;
	
	public Modifier() {
		super();
	}
	public Modifier(String id, String key, String name, String description, double price, boolean active) {
		super(id, key);
		this.name = name;
		this.description = description;
		this.price = price;
		this.active = active;
		this.type = null;
		this.typeId = null;
	}
	public Modifier(String id, String key, String name, String description, double price, boolean active, ModifierType type) {
		this(id, key, name, description, price, active);
		if (type != null) {
			this.typeId = type.getId();
		}
	}
	public Modifier(String id, String key, String name, String description, double price, boolean active, String typeId) {
		this(id, key, name, description, price, active);
		this.type = null;
		this.typeId = typeId;
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
	public ModifierType getType() {
		return type;
	}
	public void setType(ModifierType type) {
		this.type = type;
		if (type != null) {
			this.typeId = type.getId();
		}
	}
	public String getTypeId() {
		return this.typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
}
