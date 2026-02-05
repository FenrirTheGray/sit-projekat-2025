package rs.ac.singidunum.servelogic.dto.update;

import rs.ac.singidunum.servelogic.dto.AbstractFusekiDTO;

public class ModifierTypeUpdateRequestDTO extends AbstractFusekiDTO {
	
	private String id;
	private String name;
	private Boolean active;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
