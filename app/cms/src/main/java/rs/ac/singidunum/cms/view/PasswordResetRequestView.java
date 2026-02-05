package rs.ac.singidunum.cms.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import rs.ac.singidunum.cms.presenter.PasswordResetPresenter;
import rs.ac.singidunum.cms.service.PasswordResetService;

@Route(value = "forgot-password")
@StyleSheet("https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap")
@CssImport("./style/style-views.css")
@CssImport("./style/style-login.css")
public class PasswordResetRequestView extends VerticalLayout {

    private final PasswordResetPresenter presenter;
    private VerticalLayout branding;
    private EmailField emailField;
    private Button submitButton;
    private Span messageSpan;
    private RouterLink backToLogin;

    public PasswordResetRequestView(PasswordResetService service) {
        this.presenter = new PasswordResetPresenter(service);
        this.presenter.setRequestView(this);

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        addClassName("login-screen");

        Icon lockIcon = VaadinIcon.LOCK.create();
        lockIcon.addClassName("icon-pc");

        H1 title = new H1("Resetovanje lozinke");
        title.addClassName("login-title");

        branding = new VerticalLayout(lockIcon, title);
        branding.setAlignItems(Alignment.CENTER);

        emailField = new EmailField("Email");
        emailField.setPlaceholder("Unesite vasu email adresu");
        emailField.setRequiredIndicatorVisible(true);
        emailField.setWidth("300px");
        emailField.addClassName("login-field");

        submitButton = new Button("Posalji link", e -> presenter.requestPasswordReset(emailField.getValue()));
        submitButton.addClassName("login-button");
        submitButton.setWidth("300px");

        messageSpan = new Span();
        messageSpan.setVisible(false);
        messageSpan.addClassName("form-message");

        backToLogin = new RouterLink("Nazad na prijavu", LoginView.class);
        backToLogin.addClassName("link-style");

        VerticalLayout form = new VerticalLayout(branding, emailField, submitButton, messageSpan, backToLogin);
        form.setAlignItems(Alignment.CENTER);
        form.setWidth("400px");
        form.addClassName("login-form-box");

        add(form);
    }

    public void showMessage(String message, boolean isError) {
        messageSpan.setText(message);
        messageSpan.setVisible(true);
        messageSpan.removeClassName("success");
        messageSpan.removeClassName("error");
        messageSpan.addClassName(isError ? "error" : "success");

        if (!isError) {
            branding.setVisible(false);
            emailField.setVisible(false);
            submitButton.setVisible(false);
        }
    }
}
