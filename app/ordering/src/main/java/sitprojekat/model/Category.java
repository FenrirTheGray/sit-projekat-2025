package sitprojekat.model;

public class Category extends AbstractEntity{


	private String name;
    private String description;
    private boolean active;
    
    public Category() {
    	super();
    }
       
	public Category(String id, String name, String description, boolean active) {
		super(id);
		this.name = name;
		this.description = description;
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}


       
}
