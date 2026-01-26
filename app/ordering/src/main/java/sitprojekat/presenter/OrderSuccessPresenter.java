package sitprojekat.presenter;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.spring.annotation.UIScope;

import sitprojekat.interfajsi.OrderSuccessViewInterface;

@Component
@UIScope
public class OrderSuccessPresenter {

	OrderSuccessViewInterface view;
	
	
	public OrderSuccessPresenter() {
		
	}
	
	
	public void setView(OrderSuccessViewInterface orderSuccessView) {
		this.view=view;
		
	}


	public void mainScreen() {
		UI.getCurrent().navigate("Main");
	}

	
}
