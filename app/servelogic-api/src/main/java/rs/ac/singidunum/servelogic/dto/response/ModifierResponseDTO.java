package rs.ac.singidunum.servelogic.dto.response;

import rs.ac.singidunum.servelogic.dto.file.AbstractArangoDTO;

public class ModifierResponseDTO extends AbstractArangoDTO {
	
	private String name;
	private String description;
	private double price;
	private boolean active;
	private ModifierTypeResponseDTO type;
	
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
	public ModifierTypeResponseDTO getType() {
		return type;
	}
	public void setType(ModifierTypeResponseDTO type) {
		this.type = type;
	}
	
}
