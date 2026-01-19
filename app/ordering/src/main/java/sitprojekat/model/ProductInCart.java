package sitprojekat.model;

public class ProductInCart {
	
	private  Article article;
	private int numberOrdered;
	private double totalPrice;
	private double productPrice;
	private double modifierSizePrice;
	private double modifierToppingsPrice;
	
	public ProductInCart(Article article, int numberOrdered, double totalPrice,double modifierSizePrice,double modifierToppingsPrice) {
		super();
		this.article = article;
		this.numberOrdered = numberOrdered;
		this.totalPrice = totalPrice;
		this.productPrice=article.getBasePrice()+modifierSizePrice;
		this.modifierSizePrice=modifierSizePrice;
		this.modifierToppingsPrice=modifierToppingsPrice;
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

	public double getModifierToppings() {
		return modifierToppingsPrice;
	}

	public void setModifierToppings(double modifierToppings) {
		this.modifierToppingsPrice = modifierToppings;
	}

	
	
	

}
