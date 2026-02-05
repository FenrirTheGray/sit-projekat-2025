package sitprojekat.model;

import java.util.List;

public class Combo  extends Product{

	
	
	private List<Article> mainSelection;
	private List<Article> sideSelection;
	private List<Article> drinkSelection;
	
	public Combo() {
	}
	
	public Combo(String id,String name, String description, String imageUrl, double basePrice, boolean active, Category category,
			List<Article> mainSelection, List<Article> sideSelection, List<Article> drinkSelection) {
		super(id,name,description,imageUrl,basePrice,active,category);
		this.mainSelection = mainSelection;
		this.sideSelection = sideSelection;
		this.drinkSelection = drinkSelection;
	}

	public List<Article> getMainSelection() {
		return mainSelection;
	}

	public void setMainSelection(List<Article> mainSelection) {
		this.mainSelection = mainSelection;
	}

	public List<Article> getSideSelection() {
		return sideSelection;
	}

	public void setSideSelection(List<Article> sideSelection) {
		this.sideSelection = sideSelection;
	}

	public List<Article> getDrinkSelection() {
		return drinkSelection;
	}

	public void setDrinkSelection(List<Article> drinkSelection) {
		this.drinkSelection = drinkSelection;
	}








	
	
	
}
