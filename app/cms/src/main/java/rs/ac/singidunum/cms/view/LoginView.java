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

		LoginI18n login = LoginI18n.createDefault();
        login.getForm().setUsername("Email");
        login.getForm().setPassword("Lozinka");
        login.getForm().setSubmit("Prijava");
        login.getForm().setForgotPassword("Zaboravljena lozinka?");
        login.getForm().setTitle("");

        loginForm.setI18n(login);
        loginForm.addClassName("login-form-box");

		loginForm.addLoginListener(event -> {
            String email = event.getUsername();
            String password = event.getPassword();

            System.out.println("email: " + email.toLowerCase());
            System.out.println("password: " + password);

            if(this.presenter.login(email, password)){
                System.out.println("Logged in");
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

	public void showLoginError() {
		loginForm.setError(true);
	}
}
