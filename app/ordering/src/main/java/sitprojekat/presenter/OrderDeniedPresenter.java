package sitprojekat.presenter;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.spring.annotation.UIScope;

import sitprojekat.interfaces.OrderDeniedViewInterface;
import sitprojekat.view.NotificationAddToCardNotValidView;

@Component
@UIScope
public class OrderDeniedPresenter {
	
	private OrderDeniedViewInterface view;
	

	public void setView(OrderDeniedViewInterface view) {
		this.view=view;
		
	}


	public void retry() {
		NotificationAddToCardNotValidView notif=new NotificationAddToCardNotValidView();
		notif.open();
		
	}


	public void backClick() {
		UI.getCurrent().getPage().getHistory().back();
	}

}
