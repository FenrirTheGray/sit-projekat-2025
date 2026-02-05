package sitprojekat.presenter;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;

import sitprojekat.interfaces.AccountCreationViewInterface;
import sitprojekat.model.UserAccount;
import sitprojekat.service.UserAccountService;
import sitprojekat.service.UserService;

@Component
public class AccountCreationPresenter {

	private AccountCreationViewInterface view;
	private UserAccount model;
	private UserService service;
	public AccountCreationPresenter(UserService service) {
		this.service=service;
	}
	public AccountCreationViewInterface getView() {
		return view;
	}
	public void setView(AccountCreationViewInterface view) {
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
