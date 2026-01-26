package sitprojekat.interfajsi;

import java.util.List;

import sitprojekat.model.ProductInCart;

public interface CartViewInterface {
	public void showCartItems(List<ProductInCart> items);
	public void updateTotalPrice(double totalPrice);
}
