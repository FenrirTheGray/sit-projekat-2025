package sitprojekat.presenter;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;

import sitprojekat.interfajsi.AccountCreationViewInterface;
import sitprojekat.model.UserAccount;
import sitprojekat.service.UserAccountService;

@Component
public class AccountCreationPresenter {

	private AccountCreationViewInterface view;
	private UserAccount model;
	private UserAccountService service;
	public AccountCreationPresenter(UserAccountService service) {
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
	public UserAccountService getService() {
		return service;
	}
	public void setService(UserAccountService service) {
		this.service = service;
	}
	public void createAccount() {
		// u bazu posalje podatke 
	}
	public void loginScreen() {
		UI.getCurrent().navigate("LoginScreen");
	}
	
	
	
	
	
}
