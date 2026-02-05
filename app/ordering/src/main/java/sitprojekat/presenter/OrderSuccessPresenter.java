package sitprojekat.presenter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.spring.annotation.UIScope;

import sitprojekat.model.ProductInCart;
import sitprojekat.view.OrderSuccessView;

@Component
@UIScope
public class OrderSuccessPresenter {

	
	private OrderSuccessView view;
	private String orderID;
	private List<ProductInCart> productInCart;
	
	
	public OrderSuccessPresenter() {
		
	}
	
	
	public void setView(OrderSuccessView view) {
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
