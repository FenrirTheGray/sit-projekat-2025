package rs.ac.singidunum.servelogic.dto.update;

import rs.ac.singidunum.servelogic.dto.AbstractArangoDTO;

public class ModifierUpdateRequestDTO extends AbstractArangoDTO {
	
	private String name;
	private String description;
	private Double price;
	private Boolean active;
	private String typeId;
	
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
	public Boolean isActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
}
