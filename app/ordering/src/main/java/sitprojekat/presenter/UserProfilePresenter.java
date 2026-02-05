package sitprojekat.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

import sitprojekat.interfaces.UserProfileViewInterface;
import sitprojekat.model.UserAccount;
import sitprojekat.service.UserAccountService;
import sitprojekat.service.UserService;

@Component
public class UserProfilePresenter {

	private UserProfileViewInterface view;
	private UserService service;
	@Autowired 
    UserAccountService userAccountService;
	
	public UserProfilePresenter(UserService service) {
		this.service=service;

	}
	
	public void saveChanges() {
		
		if(!view.getPasswordPasswordField().isEmpty() && !view.getPasswordPasswordField().equals(view.getConfirmPasswordPasswordField())) {
			Notification notification = Notification.show("lozinka nije ista", 3000, Notification.Position.BOTTOM_START); // sta pise , koliko traje, pozicija
		    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);// koje je boje
			return;
		}
		if (view.getPasswordPasswordField().isEmpty()) {
			
	    }
		userAccountService.setPassword(view.getPasswordPasswordField());
		userAccountService.setEmail(view.getEmailField());
		userAccountService.setAdress(view.getAddressTextField());
		userAccountService.setPhone(view.getTelephoneTextField());
		
		Notification notification = Notification.show("izmenjen nalog", 3000, Notification.Position.BOTTOM_START); // sta pise , koliko traje, pozicija
	    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);// koje je boje

	}

	public void backClick() {
		UI.getCurrent().getPage().getHistory().back();
	}

	public void  LogOut() {
		service.logout();
		    
	}

	public void setView(UserProfileViewInterface view) {
		this.view=view;
		
	}
	public void updateView() {
		if(userAccountService!=null && view!=null) {
		view.setEmailField(userAccountService.getUser().getEmail());
		view.setAddressTextField(userAccountService.getUser().getAdress());
		view.setTelephoneTextField(userAccountService.getUser().getPhone());
		}
	}
}
