package sitprojekat.presenter;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;

import sitprojekat.interfaces.ForgotenPasswordViewInterface;
import sitprojekat.service.UserAccountService;

@Component
public class ForgotenPasswordPresenter {

	
	private UserAccountService service;
	private ForgotenPasswordViewInterface view;
	
	

	public ForgotenPasswordPresenter(UserAccountService service){
		super();
		this.service = service;
	}
	public UserAccountService getService() {
		return service;
	}
	public void setService(UserAccountService service) {
		this.service = service;
	}
	public ForgotenPasswordViewInterface getView() {
		return view;
	}
	public void setView(ForgotenPasswordViewInterface view) {
		this.view = view;
	}
	public void loginScreen() {
		UI.getCurrent().navigate("LoginScreen");
	}
	public void getAccountPassword() {
		// treba da vrati lozinku
	}
	
	
	
}
