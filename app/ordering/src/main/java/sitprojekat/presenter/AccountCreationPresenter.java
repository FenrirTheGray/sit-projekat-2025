package sitprojekat.presenter;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;

import sitprojekat.model.UserAccount;
import sitprojekat.service.UserService;
import sitprojekat.view.AccountCreationView;

@Component
public class AccountCreationPresenter {

	private AccountCreationView view;
	private UserAccount model;
	private UserService service;
	public AccountCreationPresenter(UserService service) {
		this.service=service;
	}
	public AccountCreationView getView() {
		return view;
	}
	public void setView(AccountCreationView view) {
		this.view = view;
	}
	public UserAccount getModel() {
		return model;
	}
	public void setModel(UserAccount model) {
		this.model = model;
	}
	public UserService getService() {
		return service;
	}
	public void setService(UserService service) {
		this.service = service;
	}
	public void createAccount(String email,String password) {
		service.createAccount(email, password);
	}
	public void loginScreen() {
		UI.getCurrent().navigate("LoginScreen");
	}
	
	
	
	
	
}
