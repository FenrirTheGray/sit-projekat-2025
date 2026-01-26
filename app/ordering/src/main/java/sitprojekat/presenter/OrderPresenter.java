package sitprojekat.presenter;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.spring.annotation.UIScope;

import sitprojekat.interfajsi.OrderViewInterface;
import sitprojekat.service.OrderService;

@Component
@UIScope
public class OrderPresenter {

	private OrderViewInterface view;
	private OrderService service;	
	
	public OrderPresenter(OrderService service) {
		this.service=service;
	}
		
	public void setView(OrderViewInterface view) {
		this.view=view;
		
	}

	public void backClick() {
		UI.getCurrent().getPage().getHistory().back();
	}

}
