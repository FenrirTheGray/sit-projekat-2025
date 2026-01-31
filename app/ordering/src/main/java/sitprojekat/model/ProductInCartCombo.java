package sitprojekat.model;

import java.util.Set;

public class ProductInCartCombo extends ProductInCart {

	private Product main;
	private Product side;
	private Product drink;

	public ProductInCartCombo(Product product, int numberOrdered, double totalPrice, Set<Modifier> modifierToppings,
			Product main, Product side, Product drink) {
		super(product, numberOrdered, totalPrice, modifierToppings);
		this.main = main;
		this.side = side;
		this.drink = drink;
	}

	public Product getMain() {
		return main;
	}

	public void setMain(Product main) {
		this.main = main;
	}

	public Product getSide() {
		return side;
	}

	public void setSide(Product side) {
		this.side = side;
	}

	public Product getDrink() {
		return drink;
	}

	public void setDrink(Product drink) {
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

		double totalPrice = (super.getProductPrice() + main.getBasePrice() + side.getBasePrice() + drink.getBasePrice()
				+ toppingModifier) * numberOrdered;
		super.setTotalPrice(totalPrice);
	}
}
