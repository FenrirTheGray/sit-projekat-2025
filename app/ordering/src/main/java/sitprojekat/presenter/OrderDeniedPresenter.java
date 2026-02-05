package sitprojekat.presenter;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.spring.annotation.UIScope;

import sitprojekat.dto.OrderResponseDTO;
import sitprojekat.service.OrderService;
import sitprojekat.service.ProductInCartService;
import sitprojekat.view.OrderDeniedView;

@Component
@UIScope
public class OrderDeniedPresenter {
	
	private OrderDeniedView view;
	private ProductInCartService cartService;
	private OrderService orderService;
	private String address;
	private String telephone;
	private String paymentType;
	
	public OrderDeniedPresenter(ProductInCartService cartService,OrderService orderService) {
		this.cartService = cartService;
		this.orderService=orderService;
	}	

	public void setView(OrderDeniedView view) {
		this.view=view;
		
	}


	public void retry() {
		OrderResponseDTO orderResponseDTO=orderService.createOrders(cartService.getProducts(),address,telephone,paymentType,cartService.getTotalPrice());
			
			if(orderResponseDTO!=null) {
				
				Notification notification = Notification.show("Porudzbina je napravljenja", 3000, Notification.Position.BOTTOM_START); // sta pise , koliko traje, pozicija
			    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);// koje je boje
				
				cartService.getProducts().clear();
				UI.getCurrent().navigate("Main");
			}
	}


	public void backClick() {
		UI.getCurrent().navigate("Cart");
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	

}
