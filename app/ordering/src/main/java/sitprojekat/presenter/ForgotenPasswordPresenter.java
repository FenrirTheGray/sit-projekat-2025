package sitprojekat.presenter;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;

import sitprojekat.interfaces.ForgotenPasswordViewInterface;
import sitprojekat.service.UserAccountService;
import sitprojekat.service.UserService;

@Component
public class ForgotenPasswordPresenter {

	
	private UserService service;
	private ForgotenPasswordViewInterface view;
	
	

	public ForgotenPasswordPresenter(UserService service){
		this.service = service;
	}
	public UserService getService() {
		return service;
	}
	public void setService(UserService service) {
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
	public void forgotPassword(String email) {
		service.forgotPassword(email);
	}
	
	
	
}
