package rs.ac.singidunum.servelogic.dto.create;

public class ModifierTypeCreateRequestDTO {
	
	private String name;
	private Boolean active;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean isActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
