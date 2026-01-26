package sitprojekat.presenter;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.spring.annotation.UIScope;

import sitprojekat.interfaces.OrderCreationViewInterface;
import sitprojekat.service.ProductInCartService;

@Component
@UIScope
public class OrderCreationPresenter {

	private OrderCreationViewInterface view;
    private ProductInCartService cartService;
    
	public OrderCreationPresenter(ProductInCartService cartService) {
		this.cartService = cartService;
	}	
    
    public void setTotalPrice() {
    	view.setTotalPrice(cartService.getTotalPrice());
    }

	public void backClick() {
		UI.getCurrent().getPage().getHistory().back();
	}

	public void setView(OrderCreationViewInterface view) {
		this.view=view;
		
	}
	

    
}
