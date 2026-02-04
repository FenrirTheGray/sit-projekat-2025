package rs.ac.singidunum.cms.presenter;

import rs.ac.singidunum.cms.service.PasswordResetService;
import rs.ac.singidunum.cms.view.PasswordResetConfirmView;
import rs.ac.singidunum.cms.view.PasswordResetRequestView;

public class PasswordResetPresenter {

    private final PasswordResetService service;
    private PasswordResetRequestView requestView;
    private PasswordResetConfirmView confirmView;

    public PasswordResetPresenter(PasswordResetService service) {
        this.service = service;
    }

    public void setRequestView(PasswordResetRequestView view) {
        this.requestView = view;
    }

    public void setConfirmView(PasswordResetConfirmView view) {
        this.confirmView = view;
    }

    public void requestPasswordReset(String email) {
        if (email == null || email.trim().isEmpty()) {
            requestView.showMessage("Unesite email adresu.", true);
            return;
        }

        boolean success = service.requestPasswordReset(email.trim());

        if (success) {
            requestView.showMessage("Ako postoji nalog sa tom email adresom, link za resetovanje je poslat.", false);
            requestView.clearForm();
        } else {
            requestView.showMessage("Doslo je do greske. Pokusajte ponovo.", true);
        }
    }

    public boolean validateToken(String token) {
        return service.validateToken(token);
    }

    public void confirmPasswordReset(String token, String password, String confirmPassword) {
        if (password == null || password.isEmpty()) {
            confirmView.showMessage("Unesite lozinku.", true);
            return;
        }

        if (!password.equals(confirmPassword)) {
            confirmView.showMessage("Lozinke se ne poklapaju.", true);
            return;
        }

        if (password.length() < 8) {
            confirmView.showMessage("Lozinka mora imati najmanje 8 karaktera.", true);
            return;
        }

        boolean success = service.confirmPasswordReset(token, password);

        if (success) {
            confirmView.showMessage("Lozinka je uspesno promenjena!", false);
            confirmView.disableForm();
        } else {
            confirmView.showMessage("Neuspesno resetovanje. Link je mozda istekao.", true);
        }
    }
}
