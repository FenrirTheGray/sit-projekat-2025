package sitprojekat.presenter;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

import sitprojekat.interfaces.LoginScreenViewInterface;
import sitprojekat.service.UserService;

@Component
public class LoginScreenPresenter {

    private  LoginScreenViewInterface view;
    private  UserService userService;

    public LoginScreenPresenter(UserService userService){
        this.userService = userService;
    }

    public void login(){
        String email = this.view.getEmailField().getValue();
        String password = this.view.getPasswordPasswordField().getValue();

        if(email == null || email.isEmpty()) {
        	Notification notification = Notification.show("email je prazan", 3000, Notification.Position.BOTTOM_CENTER); // sta pise , koliko traje, pozicija
    	    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);// koje je boje
            return ;
        }
        if(password == null || password.isEmpty()){
        	Notification notification = Notification.show("password je prazan", 3000, Notification.Position.BOTTOM_CENTER); // sta pise , koliko traje, pozicija
    	    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);// koje je boje
            return ; 
        }

        if(userService.login(email, password)) UI.getCurrent().navigate("OrderCreation");
        else {
        	Notification notification = Notification.show("uneti podaci se ne podudaraju sa nalogom", 3000, Notification.Position.BOTTOM_CENTER); // sta pise , koliko traje, pozicija
    	    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);// koje je boje
            return ;
        }
    }

    public void logout(){
        userService.logout();
    }

	public void setView(LoginScreenViewInterface view) {
		this.view=view;
		
	}

	public void forgotenPasswordScreen() {
		UI.getCurrent().navigate("ForgotenPassword");
	}

	public void accountCreationScreen() {
		UI.getCurrent().navigate("AccountCreation");
	}
}
