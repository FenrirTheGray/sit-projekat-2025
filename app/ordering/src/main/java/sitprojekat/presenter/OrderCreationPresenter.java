package sitprojekat.presenter;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.spring.annotation.UIScope;

import sitprojekat.dto.OrderResponseDTO;
import sitprojekat.interfaces.OrderCreationViewInterface;
import sitprojekat.service.OrderService;
import sitprojekat.service.ProductInCartService;
import sitprojekat.service.UserAccountService;

@Component
@UIScope
public class OrderCreationPresenter {

	private OrderCreationViewInterface view;
    private ProductInCartService cartService;
    private OrderService orderService;
    private OrderDeniedPresenter orderDeniedPresenter;
    private OrderSuccessPresenter orderSuccessPresenter;
    private UserAccountService userAccountService;
    
	public OrderCreationPresenter(ProductInCartService cartService,OrderService orderService,OrderDeniedPresenter orderDeniedPresenter,UserAccountService userAccountService,OrderSuccessPresenter orderSuccessPresenter) {
		this.cartService = cartService;
		this.orderService=orderService;
		this.orderDeniedPresenter=orderDeniedPresenter;
		this.userAccountService=userAccountService;
		this.orderSuccessPresenter=orderSuccessPresenter;
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

	public void createOrder(String address, String telephone, String paymentType) {	
		
		
		if(userAccountService.getUser().getEmail()!="") {
	
			OrderResponseDTO orderResponseDTO=orderService.createOrders(cartService.getProducts(),address,telephone,paymentType,cartService.getTotalPrice());
	
			if(orderResponseDTO!=null) {
			
				Notification notification = Notification.show("Porudzbina je napravljenja", 3000, Notification.Position.BOTTOM_START); // sta pise , koliko traje, pozicija
				notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);// koje je boje
				orderSuccessPresenter.setOrderID(orderResponseDTO.getKey());
				orderSuccessPresenter.setProductInCart(cartService.getProducts());
				
				cartService.getProducts().clear();
			
			
				UI.getCurrent().navigate("OrderSuccess");
			}
			else {
			orderDeniedPresenter.setAddress(address);
			orderDeniedPresenter.setTelephone(telephone);
			orderDeniedPresenter.setPaymentType(paymentType);
			UI.getCurrent().navigate("OrderDenied");
			}
		}
		else {
			UI.getCurrent().navigate("LoginScreen");
		}
		
		
	}
}
