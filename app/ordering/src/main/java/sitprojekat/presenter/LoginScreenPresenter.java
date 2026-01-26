package sitprojekat.presenter;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;

import sitprojekat.interfaces.LoginScreenViewInterface;
import sitprojekat.service.UserService;

@Component
public class LoginScreenPresenter {

    private  LoginScreenViewInterface view;
    private  UserService userService;

    public LoginScreenPresenter(UserService userService){
        this.userService = userService;
    }

    public boolean login(){
        String email = this.view.getEmailField().getValue();
        String password = this.view.getPasswordPasswordField().getValue();

        if(email == null || email.isEmpty()) {
            //TODO: Red border email and display error
            return false;
        }
        if(password == null || password.isEmpty()){
            //TODO: Red border password and display error
            return false;
        }

        if(userService.login(email, password)) return true;
        else {
            //TODO: Error handling
        }

        return false;
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
