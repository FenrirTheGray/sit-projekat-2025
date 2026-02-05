package rs.ac.singidunum.servelogic.dto.response;

import java.util.List;
import rs.ac.singidunum.servelogic.dto.file.AbstractArangoDTO;

public class ArticleResponseDTO extends AbstractArangoDTO{
	
	private String name;
	private String description;
	private String imageUrl;
	private double basePrice;
	private boolean active;
	private CategoryResponseDTO category;
	private List<ModifierResponseDTO> modifiers;
	
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
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public CategoryResponseDTO getCategory() {
		return category;
	}
	public void setCategory(CategoryResponseDTO category) {
		this.category = category;
	}
	public List<ModifierResponseDTO> getModifiers() {
		return modifiers;
	}
	public void setModifiers(List<ModifierResponseDTO> modifiers) {
		this.modifiers = modifiers;
	}
	
}
