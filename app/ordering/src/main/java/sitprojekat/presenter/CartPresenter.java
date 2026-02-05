package sitprojekat.presenter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.vaadin.flow.spring.annotation.UIScope;

import sitprojekat.model.ProductInCart;
import sitprojekat.service.ProductInCartService;
import sitprojekat.view.CartView;
import sitprojekat.view.NotificationChoiceConfirmationDeleteFromCart;

@Component
@UIScope
public class CartPresenter {

	private CartView view;
    private ProductInCartService cartService;
    
    public CartPresenter( ProductInCartService cartService) {
        this.cartService = cartService;
    }
    public void updateCart() {   // menja se ukupna cena za sve proizvode
        List<ProductInCart> productsInCart = cartService.getProducts();
        
        view.showCartItems(productsInCart);
        
        view.updateTotalPrice(cartService.getTotalPrice());
    }
    public void removeFromCart(ProductInCart product,int removeAmount) {
    	NotificationChoiceConfirmationDeleteFromCart notif=new NotificationChoiceConfirmationDeleteFromCart();
    	notif.addConfirmListener(e->{ // sta radni dugme yes
    		if (product.getNumberOrdered() > removeAmount) { //ako ima idalje kolicinu samo se smanji broj
        		product.updateOrderedAmount(product.getNumberOrdered() -removeAmount);
        	} else {
        		cartService.removeProduct(product);//brise se ako nema
        	}
    		notif.close();
        	updateCart(); 
    	});
    	
    	notif.open();
    }
    
    public List<ProductInCart> getProducts(){
    	return cartService.getProducts();
    	
    }
	public void setView(CartView view) {
		this.view=view;
		
	}
}

