package sitprojekat.presenter;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;

import sitprojekat.interfaces.ForgotenPasswordViewInterface;
import sitprojekat.service.PasswordResetService;
import sitprojekat.service.UserAccountService;

@Component
public class ForgotenPasswordPresenter {


	private UserAccountService service;
	private PasswordResetService passwordResetService;
	private ForgotenPasswordViewInterface view;


	public ForgotenPasswordPresenter(UserAccountService service, PasswordResetService passwordResetService){
		super();
		this.service = service;
		this.passwordResetService = passwordResetService;
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
		String email = view.getEmailField().getValue();

		if (email == null || email.trim().isEmpty()) {
			view.showMessage("Unesite email adresu.", true);
			return;
		}

		boolean success = passwordResetService.requestPasswordReset(email.trim());

		if (success) {
			view.showMessage("Ako postoji nalog sa tom email adresom, link za resetovanje je poslat.", false);
			view.clearForm();
		} else {
			view.showMessage("Doslo je do greske. Pokusajte ponovo.", true);
		}
	}

}
