package rs.ac.singidunum.cms.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import rs.ac.singidunum.cms.presenter.PasswordResetPresenter;
import rs.ac.singidunum.cms.service.PasswordResetService;

import java.util.List;
import java.util.Map;

@Route(value = "reset-password")
@StyleSheet("https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap")
@CssImport("./style/style-views.css")
@CssImport("./style/style-login.css")
public class PasswordResetConfirmView extends VerticalLayout implements BeforeEnterObserver {

    private final PasswordResetPresenter presenter;
    private VerticalLayout branding;
    private PasswordField passwordField;
    private PasswordField confirmPasswordField;
    private Button submitButton;
    private Span messageSpan;
    private RouterLink backToLogin;
    private String token;

    public PasswordResetConfirmView(PasswordResetService service) {
        this.presenter = new PasswordResetPresenter(service);
        this.presenter.setConfirmView(this);

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        addClassName("login-screen");

        Icon lockIcon = VaadinIcon.KEY.create();
        lockIcon.addClassName("icon-pc");

        H1 title = new H1("Nova lozinka");
        title.addClassName("login-title");

        branding = new VerticalLayout(lockIcon, title);
        branding.setAlignItems(Alignment.CENTER);

        passwordField = new PasswordField("Nova lozinka");
        passwordField.setRequiredIndicatorVisible(true);
        passwordField.setWidth("300px");
        passwordField.addClassName("login-field");

        confirmPasswordField = new PasswordField("Potvrdite lozinku");
        confirmPasswordField.setRequiredIndicatorVisible(true);
        confirmPasswordField.setWidth("300px");
        confirmPasswordField.addClassName("login-field");

        submitButton = new Button("Promeni lozinku", e ->
            presenter.confirmPasswordReset(token, passwordField.getValue(), confirmPasswordField.getValue())
        );
        submitButton.addClassName("login-button");
        submitButton.setWidth("300px");

        messageSpan = new Span();
        messageSpan.setVisible(false);
        messageSpan.addClassName("form-message");

        backToLogin = new RouterLink("Povratak na Prijavu", LoginView.class);
        backToLogin.addClassName("link-style");

        VerticalLayout form = new VerticalLayout(branding, passwordField, confirmPasswordField,
                submitButton, messageSpan, backToLogin);
        form.setAlignItems(Alignment.CENTER);
        form.setWidth("400px");
        form.addClassName("login-form-box");

        add(form);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Map<String, List<String>> params = event.getLocation().getQueryParameters().getParameters();
        List<String> tokens = params.get("token");

        if (tokens == null || tokens.isEmpty()) {
            showMessage("Neispravan link za resetovanje!", true);
            disableForm();
            return;
        }

        this.token = tokens.get(0);

        if (!presenter.validateToken(token)) {
            showMessage("Link za resetovanje je istekao ili je vec iskoriscen.", true);
            disableForm();
        }
    }

    public void showMessage(String message, boolean isError) {
        messageSpan.setText(message);
        messageSpan.setVisible(true);
        messageSpan.removeClassName("success");
        messageSpan.removeClassName("error");
        messageSpan.addClassName(isError ? "error" : "success");
    }

    public void hideForm() {
        branding.setVisible(false);
        passwordField.setVisible(false);
        confirmPasswordField.setVisible(false);
        submitButton.setVisible(false);
    }

    public void disableForm() {
        submitButton.setEnabled(false);
        passwordField.setEnabled(false);
        confirmPasswordField.setEnabled(false);
    }
}
