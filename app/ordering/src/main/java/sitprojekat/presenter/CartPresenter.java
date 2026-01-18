package sitprojekat.presenter;

import java.util.List;

import sitprojekat.interfajsi.CartViewInterface;
import sitprojekat.model.ProductInCart;
import sitprojekat.service.ProductInCartService;

public class CartPresenter {

	private final CartViewInterface view;
    private final ProductInCartService cartService;
    
    public CartPresenter(CartViewInterface view, ProductInCartService cartService) {
        this.view = view;
        this.cartService = cartService;
    }
    public void updateCart() {   // menja se ukupna cena za sve proizvode
        List<ProductInCart> productsInCart = cartService.getProducts();
        
        view.showCartItems(productsInCart);
        
        double totalPrice = 0;
        for (ProductInCart product : productsInCart) {
            totalPrice += product.getTotalPrice();
        }
        view.updateTotalPrice(totalPrice);
    }
    public void removeFromCart(ProductInCart product,int removeAmount) {
    	if (product.getNumberOrdered() > removeAmount) { //ako ima idalje kolicinu samo se smanji broj
    		product.updateOrderedAmount(product.getNumberOrdered() -removeAmount);
    	} else {
    		cartService.removeProduct(product);//brise se ako nema
    	}
    	updateCart(); 
    }
}

