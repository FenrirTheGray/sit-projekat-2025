package sitprojekat.presenter;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

import sitprojekat.interfaces.UserProfileViewInterface;
import sitprojekat.model.UserAccount;
import sitprojekat.service.UserService;


public class UserProfilePresenter {

	private UserProfileViewInterface view;
	private UserAccount user;
	private UserService service;
	
	
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
		user.setPassword(view.getPasswordPasswordField());
		user.setEmail(view.getEmailField());
		user.setAdress(view.getAddressTextField());
		user.setPhone(view.getTelephoneTextField());
		
		Notification notification = Notification.show("izmenjen nalog", 3000, Notification.Position.BOTTOM_START); // sta pise , koliko traje, pozicija
	    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);// koje je boje

	}

	public void backClick() {
		UI.getCurrent().getPage().getHistory().back();
	}

	public void  LogOut() {
		service.logout();
		    
	}

	public void setView(UserProfileViewInterface userProfileView) {
		this.view=view;
		
	}
}
