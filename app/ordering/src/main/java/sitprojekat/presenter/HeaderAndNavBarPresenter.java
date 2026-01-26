package sitprojekat.presenter;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;

import sitprojekat.interfajsi.HeaderAndNavBarInterface;

@Component
public class HeaderAndNavBarPresenter {

	private HeaderAndNavBarInterface view;
	
	public HeaderAndNavBarPresenter() {
	}

	public void setView(HeaderAndNavBarInterface view) {
		this.view=view;
		
	}
	public void userProfileScreen() {
		UI.getCurrent().navigate("UserProfile");
	}
	public void shoppingCartScreen() {
		UI.getCurrent().navigate("Cart");
	}

}
