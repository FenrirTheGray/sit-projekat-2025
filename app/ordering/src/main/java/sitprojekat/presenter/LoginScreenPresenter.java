package sitprojekat.presenter;

import sitprojekat.service.UserService;
import sitprojekat.view.LoginScreenView;

public class LoginScreenPresenter {

    private final LoginScreenView view;
    private final UserService userService;

    public LoginScreenPresenter(LoginScreenView view, UserService userService){
        this.view = view;
        this.userService = userService;
    }

    public boolean login(){
        String email = this.view.getEmail();
        String password = this.view.getPassword();

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
        throw new UnsupportedOperationException();
    }
}
