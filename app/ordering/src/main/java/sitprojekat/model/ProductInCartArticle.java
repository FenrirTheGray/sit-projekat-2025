package sitprojekat.model;

import java.util.Set;

public class ProductInCartArticle extends ProductInCart{

	
	private  Modifier modifierSize;


	public ProductInCartArticle(Article product, int numberOrdered, double totalPrice, Set<Modifier> modifierToppings,Modifier modifierSize) {
		super(product, numberOrdered, totalPrice, modifierToppings);
		this.modifierSize = modifierSize;
	}


	public Modifier getModifierSize() {
		return modifierSize;
	}


	public void setModifierSize(Modifier modifierSize) {
		this.modifierSize = modifierSize;
	}

	
	
	

}
