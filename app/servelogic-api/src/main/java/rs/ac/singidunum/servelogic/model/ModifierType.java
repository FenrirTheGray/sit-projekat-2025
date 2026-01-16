package rs.ac.singidunum.servelogic.model;

public class ModifierType extends AbstractFusekiEntity {

	private String name;
	private boolean active;

	public ModifierType() {
		super();
	}
	public ModifierType(String id, String name, boolean active) {
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
