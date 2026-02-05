package rs.ac.singidunum.cms.view;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import rs.ac.singidunum.cms.presenter.LoginPresenter;
import rs.ac.singidunum.cms.service.UserService;

@Route(value = "")
@StyleSheet("https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap")
@CssImport("./style/style-views.css")
@CssImport("./style/style-login.css")
public class LoginView extends VerticalLayout {

    private static final long serialVersionUID = 6313156717813295316L;

    private final LoginPresenter presenter;
    private LoginForm loginForm;
    private final LoginI18n loginI18n;

		public LoginView(UserService userService) {
        this.presenter = new LoginPresenter(this, userService);

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        addClassName("login-screen");

        Icon computerIcon = VaadinIcon.DESKTOP.create();
        computerIcon.addClassName("icon-pc");

        H1 title = new H1("ServeLogic CMS");
        title.addClassName("login-title");

		VerticalLayout branding = new VerticalLayout(computerIcon, title);
		branding.setAlignItems(Alignment.CENTER);

		loginForm = new LoginForm();

		loginI18n = LoginI18n.createDefault();
		loginI18n.getForm().setUsername("Email");
		loginI18n.getForm().setPassword("Lozinka");
		loginI18n.getForm().setSubmit("Prijava");
		loginI18n.getForm().setForgotPassword("Zaboravljena lozinka?");
		loginI18n.getForm().setTitle("");

		LoginI18n.ErrorMessage errorMessage = loginI18n.getErrorMessage();
		errorMessage.setTitle("Pogrešni podaci za prijavu");
		errorMessage.setMessage("Proverite da li su email i lozinka ispravni i pokušajte ponovo.");
		loginI18n.setErrorMessage(errorMessage);

		loginForm.setI18n(loginI18n);
		loginForm.addClassName("login-form-box");

		loginForm.addLoginListener(event -> {
			clearError();

			String email = event.getUsername();
			String password = event.getPassword();

			if(this.presenter.login(email, password)){
				getUI().ifPresent(ui -> ui.navigate(ArticlesView.class));
			}
		});

		loginForm.addForgotPasswordListener(event -> {
			getUI().ifPresent(ui -> ui.navigate(PasswordResetRequestView.class));
		});

		HorizontalLayout content = new HorizontalLayout(branding, loginForm);
		content.setAlignItems(Alignment.CENTER);
		content.setSpacing(true);

		add(content);
    }

    public void showLoginError(String title, String message) {
        LoginI18n.ErrorMessage errorMessage = loginI18n.getErrorMessage();
        errorMessage.setTitle(title);
        errorMessage.setMessage(message);
        loginI18n.setErrorMessage(errorMessage);

        loginForm.setI18n(loginI18n);
        loginForm.setError(true);
    }

    public void showLoginError() {
        showLoginError("Pogrešni podaci za prijavu", "Proverite da li su email i lozinka ispravni i pokušajte ponovo.");
    }

    public void clearError() {
        loginForm.setError(false);
    }
}
