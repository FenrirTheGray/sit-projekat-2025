package sitprojekat.presenter;

import org.springframework.stereotype.Component;

import com.vaadin.flow.spring.annotation.UIScope;

import sitprojekat.interfajsi.OrdersViewInterface;


@Component
@UIScope
public class OrdersPresenter {

	
	
	
	private OrdersViewInterface view;
	
	public OrdersPresenter() {
		
	}
	
	
	public void setView(OrdersViewInterface view) {
		this.view=view;
		
	}

}
