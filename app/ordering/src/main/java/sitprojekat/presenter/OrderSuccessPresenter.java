package sitprojekat.presenter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.spring.annotation.UIScope;

import sitprojekat.interfaces.OrderSuccessViewInterface;
import sitprojekat.model.ProductInCart;

@Component
@UIScope
public class OrderSuccessPresenter {

	
	private OrderSuccessViewInterface view;
	private String orderID;
	private List<ProductInCart> productInCart;
	
	
	public OrderSuccessPresenter() {
		
	}
	
	
	public void setView(OrderSuccessViewInterface view) {
		this.view=view;
		
	}


	public void mainScreen() {
		UI.getCurrent().navigate("Main");
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}



	public void setProductInCart(List<ProductInCart> productInCart) {
		this.productInCart = productInCart;
	}


	public void updateView() {
		view.setOrderIDSpan(this.orderID);
		view.setItemsOrderedSpan(this.productInCart);
		
	}
	
	
}
