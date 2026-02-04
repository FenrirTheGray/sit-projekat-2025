package sitprojekat.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import sitprojekat.service.PasswordResetService;

import java.util.List;
import java.util.Map;

@StyleSheet("https://fonts.googleapis.com/css?family=Kaushan+Script")
@Route("ResetPassword")
public class ResetPasswordView extends VerticalLayout implements BeforeEnterObserver {

    private static final long serialVersionUID = 1L;

    private PasswordField passwordField = new PasswordField();
    private PasswordField confirmPasswordField = new PasswordField();
    private Button submitButton = new Button();
    private Span messageSpan = new Span();
    private String token;
    private PasswordResetService passwordResetService;

    public ResetPasswordView(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;

        addClassName("greenBackground");
        setSizeFull();

        H2 titleH2 = new H2();
        titleH2.setText("Ordering app");
        titleH2.addClassName("whiteTextNotif");

        Icon keyIcon = VaadinIcon.KEY.create();
        keyIcon.addClassName("icon");

        passwordField.setPlaceholder("Nova lozinka");
        passwordField.setPrefixComponent(keyIcon);
        passwordField.addClassName("inputField");

        Icon keyIcon2 = VaadinIcon.KEY.create();
        keyIcon2.addClassName("icon");

        confirmPasswordField.setPlaceholder("Potvrdite lozinku");
        confirmPasswordField.setPrefixComponent(keyIcon2);
        confirmPasswordField.addClassName("inputField");

        VerticalLayout resetPasswordContainer = new VerticalLayout();

        submitButton.setText("Promeni lozinku");
        submitButton.addClassName("brownButton");
        submitButton.addClickListener(e -> confirmPasswordReset());

        messageSpan.setVisible(false);

        Span loginSpan = new Span();
        loginSpan.setText("Nazad na prijavu");
        loginSpan.addClassName("loginSpan");
        loginSpan.addClickListener(e -> UI.getCurrent().navigate("LoginScreen"));

        resetPasswordContainer.add(passwordField, confirmPasswordField, submitButton, messageSpan, loginSpan);

        resetPasswordContainer.addClassName("orderingContainer");
        resetPasswordContainer.setPadding(true);
        resetPasswordContainer.setAlignItems(Alignment.STRETCH);

        VerticalLayout orderContainer = new VerticalLayout();
        orderContainer.add(titleH2, resetPasswordContainer);
        orderContainer.setAlignItems(Alignment.CENTER);
        orderContainer.setJustifyContentMode(JustifyContentMode.CENTER);

        add(orderContainer);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Map<String, List<String>> params = event.getLocation().getQueryParameters().getParameters();
        List<String> tokens = params.get("token");

        if (tokens == null || tokens.isEmpty()) {
            showMessage("Neispravan link za resetovanje.", true);
            disableForm();
            return;
        }

        this.token = tokens.get(0);

        if (!passwordResetService.validateToken(token)) {
            showMessage("Link za resetovanje je istekao ili je vec iskoriscen.", true);
            disableForm();
        }
    }

    private void confirmPasswordReset() {
        String password = passwordField.getValue();
        String confirmPassword = confirmPasswordField.getValue();

        if (password == null || password.isEmpty()) {
            showMessage("Unesite lozinku.", true);
            return;
        }

        if (!password.equals(confirmPassword)) {
            showMessage("Lozinke se ne poklapaju.", true);
            return;
        }

        if (password.length() < 8) {
            showMessage("Lozinka mora imati najmanje 8 karaktera.", true);
            return;
        }

        boolean success = passwordResetService.confirmPasswordReset(token, password);

        if (success) {
            showMessage("Lozinka je uspesno promenjena!", false);
            disableForm();
        } else {
            showMessage("Neuspesno resetovanje. Link je mozda istekao.", true);
        }
    }

    private void showMessage(String message, boolean isError) {
        messageSpan.setText(message);
        messageSpan.setVisible(true);
        messageSpan.getStyle().set("color", isError ? "#c62828" : "#2e7d32");
        messageSpan.getStyle().set("text-align", "center");
    }

    private void disableForm() {
        submitButton.setEnabled(false);
        passwordField.setEnabled(false);
        confirmPasswordField.setEnabled(false);
    }
}
