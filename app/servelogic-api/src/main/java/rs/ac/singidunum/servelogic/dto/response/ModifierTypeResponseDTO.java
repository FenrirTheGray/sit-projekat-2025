package rs.ac.singidunum.servelogic.dto.response;

import rs.ac.singidunum.servelogic.dto.file.AbstractFusekiDTO;

public class ModifierTypeResponseDTO extends AbstractFusekiDTO {
	
	private String name;
	private boolean active;
	
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
