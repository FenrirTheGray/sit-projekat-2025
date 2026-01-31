package sitprojekat.model;

import java.util.Set;

public abstract class ProductInCart {
	
	private  Product product;
	private int numberOrdered;
	private double totalPrice;
	private double productPrice;
    private Set<Modifier> modifierToppings;
	private double modifierSizePrice;
	private double modifierToppingsPrice;
	

	
	public ProductInCart(Product product, int numberOrdered, double totalPrice,Set<Modifier> modifierToppings) {
		this.product = product;
		this.numberOrdered = numberOrdered;
		this.totalPrice = totalPrice;
		this.productPrice=product.getBasePrice();
		this.modifierToppings=modifierToppings;
		
		double toppingModifier = 0;
	    if (modifierToppings != null) {
	        for (Modifier modifier : modifierToppings) {
	        	toppingModifier += modifier.getPrice();
	        }
	    }
	    this.modifierToppingsPrice = toppingModifier;
	}



	public Product getProduct() {
		return product;
	}

	public void setArticle(Product article) {
		this.product = article;
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
        this.totalPrice = (this.productPrice+this.modifierSizePrice+this.modifierToppingsPrice) * numberOrdered;
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


	public Set<Modifier> getModifierToppings() {
		return modifierToppings;
	}

	public void setModifierToppings(Set<Modifier> modifierToppings) {
		this.modifierToppings = modifierToppings;
	}





	

	

}
