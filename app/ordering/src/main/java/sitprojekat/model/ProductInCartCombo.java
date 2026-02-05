package sitprojekat.model;

import java.util.Set;

public class ProductInCartCombo extends ProductInCart {

	private ProductInCart main;
	private ProductInCart side;
	private ProductInCart drink;

	public ProductInCartCombo(Product product, int numberOrdered, double totalPrice,
			ProductInCart main, ProductInCart side, ProductInCart drink) {
		super(product, numberOrdered, totalPrice,null);
		this.main = main;
		this.side = side;
		this.drink = drink;
	}



	public ProductInCart getMain() {
		return main;
	}



	public void setMain(ProductInCart main) {
		this.main = main;
	}



	public ProductInCart getSide() {
		return side;
	}



	public void setSide(ProductInCart side) {
		this.side = side;
	}



	public ProductInCart getDrink() {
		return drink;
	}



	public void setDrink(ProductInCart drink) {
		this.drink = drink;
	}



	public void updateOrderedAmount(int numberOrdered) {
		super.setNumberOrdered(numberOrdered);

		double toppingModifier = 0;
		if (super.getModifierToppings() != null) {
			for (Modifier modifier : super.getModifierToppings()) {
				toppingModifier += modifier.getPrice();
			}
		}
		super.setModifierToppingsPrice(toppingModifier);

		double totalPrice = (super.getProductPrice() + main.getProduct().getBasePrice() + side.getProduct().getBasePrice() + drink.getProduct().getBasePrice()
				+ toppingModifier) * numberOrdered;
		super.setTotalPrice(totalPrice);
	}
}
