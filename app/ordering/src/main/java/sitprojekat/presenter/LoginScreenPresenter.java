package sitprojekat.presenter;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;

import sitprojekat.interfaces.LoginScreenViewInterface;
import sitprojekat.service.UserAccountService;

@Component
public class LoginScreenPresenter {

	private LoginScreenViewInterface view;
	private UserAccountService service;
	
	
	public LoginScreenPresenter(UserAccountService service) {
		super();
		this.service = service;
	}
	
	public LoginScreenViewInterface getView() {
		return view;
	}


	public void setView(LoginScreenViewInterface view) {
		this.view = view;
	}


	public UserAccountService getService() {
		return service;
	}


	public void setService(UserAccountService service) {
		this.service = service;
	}

	public void forgotenPasswordScreen() {
		UI.getCurrent().navigate("ForgotenPassword");
	}

	public void accountCreationScreen() {
		UI.getCurrent().navigate("AccountCreation");
	}

	public void login() {
		// gledaju se podaci sa bazom pa  se loguje
	}
	
	
}
