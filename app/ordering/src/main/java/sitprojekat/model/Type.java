package sitprojekat.model;

public class Type extends AbstractEntity{
	
	private String name;
	private boolean active;
	
	public Type() {
    	super();
    }
	 
	public Type(String id, String name, boolean active) {
		super(id);
		this.name = name;
		this.active = active;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

    

}
