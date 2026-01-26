package sitprojekat.model;

import java.util.Set;

public class ProductInCart {
	
	private  Article article;
	private int numberOrdered;
	private double totalPrice;
	private double productPrice;
	private Modifier modifierSize;
    private Set<Modifier> modifierToppings; //jedinstvena lista  za sad razmiljsam sta da radi
	private double modifierSizePrice;
	private double modifierToppingsPrice;
	
	public ProductInCart(Article article, int numberOrdered, double totalPrice,Modifier modifierSize,Set<Modifier> modifierToppings) {
		this.article = article;
		this.numberOrdered = numberOrdered;
		this.totalPrice = totalPrice;
		this.productPrice=article.getBasePrice();
		this.modifierSize=modifierSize;
		this.modifierToppings=modifierToppings;
		
		if(modifierSize != null) {
			this.modifierSizePrice=this.modifierSize.getPrice();
		}
		double toppingModifier = 0;
	    if (modifierToppings != null) {
	        for (Modifier modifier : modifierToppings) {
	        	toppingModifier += modifier.getPrice();
	        }
	    }
	    this.modifierToppingsPrice = toppingModifier;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public int getNumberOrdered() {
		return numberOrdered;
	}

	public void setNumberOrdered(int numberOrdered) {
		this.numberOrdered = numberOrdered;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public void updateOrderedAmount(int numberOrdered) {
        this.numberOrdered = numberOrdered;
        this.totalPrice = (this.article.getBasePrice()+this.modifierSizePrice+this.modifierToppingsPrice) * numberOrdered;
    }

	public double getModifierSizePrice() {
		return modifierSizePrice;
	}

	public void setModifierSizePrice(double modifierSizePrice) {
		this.modifierSizePrice = modifierSizePrice;
	}

	public double getModifierToppingsPrice() {
		return modifierToppingsPrice;
	}

	public void setModifierToppingsPrice(double modifierToppings) {
		this.modifierToppingsPrice = modifierToppings;
	}

	public Modifier getModifierSize() {
		return modifierSize;
	}

	public void setModifierSize(Modifier modifierSize) {
		this.modifierSize = modifierSize;
	}

	public Set<Modifier> getModifierToppings() {
		return modifierToppings;
	}

	public void setModifierToppings(Set<Modifier> modifierToppings) {
		this.modifierToppings = modifierToppings;
	}

	

}
