package rs.ac.singidunum.cms.presenter;

import java.util.regex.Pattern;

import rs.ac.singidunum.cms.service.UserService;
import rs.ac.singidunum.cms.view.LoginView;

public class LoginPresenter {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

    private final LoginView view;
    private final UserService userService;

    public LoginPresenter(LoginView view, UserService userService){
        this.view = view;
        this.userService = userService;
    }

    public boolean login(String email, String password){
        String sanitizedEmail = email == null ? "" : email.trim();
        String sanitizedPassword = password == null ? "" : password.trim();

        if(sanitizedEmail.isBlank()) {
            view.showLoginError("Email nedostaje", "Unesite svoju email adresu.");
            return false;
        }

        if(!EMAIL_PATTERN.matcher(sanitizedEmail).matches()) {
            view.showLoginError("Neispravan email", "Format email adrese nije ispravan. Primer: korisnik@domen.com");
            return false;
        }

        if(sanitizedPassword.isBlank()){
            view.showLoginError("Lozinka nedostaje", "Unesite lozinku.");
            return false;
        }

        try {
            if(userService.login(sanitizedEmail, sanitizedPassword)) {
                return true;
            } else {
                view.showLoginError("Pogrešni podaci za prijavu", "Proverite da li su email i lozinka ispravni i pokušajte ponovo.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.showLoginError("Greška servera", "Prijavljivanje trenutno nije moguće. Pokušajte ponovo kasnije.");
            return false;
        }
    }
    public void logout(){
        userService.logout();
    }
}
