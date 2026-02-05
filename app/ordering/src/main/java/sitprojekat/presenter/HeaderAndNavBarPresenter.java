package sitprojekat.presenter;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;

import sitprojekat.view.HeaderAndNavBar;

@Component
public class HeaderAndNavBarPresenter {

	private HeaderAndNavBar view;
	
	public HeaderAndNavBarPresenter() {
	}

	public void setView(HeaderAndNavBar view) {
		this.view=view;
		
	}
	public void userProfileScreen() {
		UI.getCurrent().navigate("UserProfile");
	}
	public void shoppingCartScreen() {
		UI.getCurrent().navigate("Cart");
	}

}
