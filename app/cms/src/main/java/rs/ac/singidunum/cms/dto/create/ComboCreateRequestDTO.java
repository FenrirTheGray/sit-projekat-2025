package rs.ac.singidunum.cms.dto.create;

import java.util.List;

public class ComboCreateRequestDTO {

	private String name;
	private String description;
	private String imageUrl;
	private Double basePrice;
	private Boolean active;
	private List<String> mainSelection;
	private List<String> sideSelection;
	private List<String> drinkSelection;

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

	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<String> getMainSelection() {
		return mainSelection;
	}

	public void setMainSelection(List<String> mainSelection) {
		this.mainSelection = mainSelection;
	}

	public List<String> getSideSelection() {
		return sideSelection;
	}

	public void setSideSelection(List<String> sideSelection) {
		this.sideSelection = sideSelection;
	}

	public List<String> getDrinkSelection() {
		return drinkSelection;
	}

	public void setDrinkSelection(List<String> drinkSelection) {
		this.drinkSelection = drinkSelection;
	}
}
