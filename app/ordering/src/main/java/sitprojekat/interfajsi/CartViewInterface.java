package sitprojekat.interfajsi;

import java.util.List;

import sitprojekat.model.ProductInCart;

public interface CartViewInterface {
	void showCartItems(List<ProductInCart> items);
	void updateTotalPrice(double totalPrice);
}
