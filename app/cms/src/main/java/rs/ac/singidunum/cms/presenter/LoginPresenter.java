package rs.ac.singidunum.cms.presenter;

import rs.ac.singidunum.cms.service.UserService;
import rs.ac.singidunum.cms.view.LoginView;

public class LoginPresenter {

    private final LoginView view;
    private final UserService userService;

    public LoginPresenter(LoginView view, UserService userService){
        this.view = view;
        this.userService = userService;
    }

    public boolean login(String email, String password){
        if(email == null || email.isEmpty()) {
            view.showLoginError();
            return false;
        }
        if(password == null || password.isEmpty()){
            view.showLoginError();
            return false;
        }

        if(userService.login(email, password)) {
            return true;
        } else {
            view.showLoginError();
            return false;
        }
    }
    public void logout(){
        userService.logout();
    }
}
