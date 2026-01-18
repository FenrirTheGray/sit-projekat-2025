package sitprojekat.model;

public class Modifier extends AbstractEntity{

	
    private String name;
    private String description;
    private Double price;
    private boolean active;
    private Type type;
    
    public Modifier() {
		super();
	}
	public Modifier(String id, String name, String description, Double price, boolean active, Type type) {
		super(id);
		this.name = name;
		this.description = description;
		this.price = price;
		this.active = active;
		this.type = type;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type Type) {
		this.type = Type;
	}

    

}
