package sitprojekat.model;

import java.util.List;

public class Combo  extends Product{

	
	
	private List<Article> main;
	private List<Article> side;
	private List<Article> drink;
	
	public Combo() {
	}
	
	public Combo(String id,String name, String description, String imageUrl, double basePrice, boolean active, Category category,
			List<Article> mainDish, List<Article> sideDish, List<Article> drink) {
		super(id,name,description,imageUrl,basePrice,active,category);
		this.main = mainDish;
		this.side = sideDish;
		this.drink = drink;
	}




	public List<Article> getMain() {
		return main;
	}

	public void setMain(List<Article> main) {
		this.main = main;
	}

	public List<Article> getSide() {
		return side;
	}

	public void setSide(List<Article> side) {
		this.side = side;
	}

	public List<Article> getMainDish() {
		return main;
	}
	public void setMainDish(List<Article> mainDish) {
		this.main = mainDish;
	}
	public List<Article> getSideDish() {
		return side;
	}
	public void setSideDish(List<Article> sideDish) {
		this.side = sideDish;
	}
	public List<Article> getDrink() {
		return drink;
	}
	public void setDrink(List<Article> drink) {
		this.drink = drink;
	}


	
	
	
}
