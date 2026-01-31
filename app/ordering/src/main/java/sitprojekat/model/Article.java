package sitprojekat.model;

import java.util.List;

public class Article extends Product{

	
	private List<Modifier> modifiers;

	public Article() {
		super();
	}


	public Article(String id, String name, String description, String imageUrl, double basePrice, boolean active,Category category,List<Modifier> modifiers) {
		super(id, name, description, imageUrl, basePrice, active, category);
		this.modifiers=modifiers;
	}





	public List<Modifier> getModifiers() {
		return modifiers;
	}

	public void setModifiers(List<Modifier> modifiers) {
		this.modifiers = modifiers;
	}

	
	

}
