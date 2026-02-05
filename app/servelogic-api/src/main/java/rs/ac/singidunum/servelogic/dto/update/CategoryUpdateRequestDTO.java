package rs.ac.singidunum.servelogic.dto.update;

import rs.ac.singidunum.servelogic.dto.file.AbstractFusekiDTO;

public class CategoryUpdateRequestDTO extends AbstractFusekiDTO {
	
	private String name;
	private String description;
	private Boolean active;
	
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
	public Boolean isActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
