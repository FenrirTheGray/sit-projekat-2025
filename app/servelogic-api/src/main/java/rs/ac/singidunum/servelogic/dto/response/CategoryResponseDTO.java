package rs.ac.singidunum.servelogic.dto.response;

import rs.ac.singidunum.servelogic.dto.AbstractFusekiDTO;

public class CategoryResponseDTO extends AbstractFusekiDTO {
	
	private String name;
	private String description;
	private boolean active;
	
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
