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

@Route(value = "") // početna stranica
@StyleSheet("https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap") //import od googla za font
@CssImport("./style/style-login.css") // import css-a
public class LoginView extends VerticalLayout {

    // Vertical Layout = jedno ispod drugog
    // Horizontal Layout = jedno pored drugog

	private static final long serialVersionUID = 6313156717813295316L;

    private final LoginPresenter presenter;

	public LoginView(UserService userService) {

        this.presenter = new LoginPresenter(this, userService);
        // postavljanje podešavanja canvas-a (platna)
        setSizeFull(); // postavljanje platna preko celog  (da zauzme 100% širine i visine ekrana)
        setAlignItems(Alignment.CENTER); // centriranje sadržaja horizontalno (Levo - Desno)
        setJustifyContentMode(JustifyContentMode.CENTER); // centriranje sadržaja vertikalno (Gore - Dole)

        // postavljanje klase za pozadinu (čiju boju menjamo u style.css)
        addClassName("login-screen");

        // postavljanje tamne teme
		getElement().getThemeList().add("dark");

        // KOMPONENTE
        // ikonica
        Icon computerIcon = VaadinIcon.DESKTOP.create();
        computerIcon.addClassName("icon-pc"); // dodeljujemo joj klasu

        // naslov
        H1 title = new H1("ServeLogic CMS");
        title.addClassName("login-title");

        // mala kutija 1: branding (box) - vertical - ikonica i naslov (h1)
		VerticalLayout branding = new VerticalLayout(computerIcon, title);
        // branding.addClassName("branding-box");
		branding.setAlignItems(Alignment.CENTER);

        // mala kutija 2: login-form (box) - vertical (podrazumevano - jer je gotova komponenta) - forma (sa elementima)
		LoginForm loginForm = new LoginForm(); // kreiramo formu (u koju ćemo da ubacimo formular)
		loginForm.getElement().getThemeList().add("light dark");

        // Internationalization (I 18slova n)
        // DTO = Data Transfer Object
		LoginI18n login = LoginI18n.createDefault(); // kreiranje formulara, ali na engleskom (a mi hoćemo da bude na srpskom)
        login.getForm().setUsername("Email"); // Username -> Email
        login.getForm().setPassword("Lozinka"); // Password -> Lozinka
        login.getForm().setSubmit("Prijava"); // Log In -> Prijava
        login.getForm().setForgotPassword("Zaboravljena lozinka?"); // Forgot password -> Zaboravljena lozinka
        login.getForm().setTitle(""); // brišemo podrazumevani naslov

        // u formu ubacujemo formular na srpskom (umesto da bude na engleskom)
        loginForm.setI18n(login);
        loginForm.addClassName("login-form-box"); // pravimo klasu za css

        // listener za logovanje
		loginForm.addLoginListener(event -> {
            String email = event.getUsername();
            String password = event.getPassword();
            
            System.out.println("email: " + email.toLowerCase()); // testiramo ispis emaila u konzolu u IDE
            System.out.println("password: " + password);

            if(this.presenter.login(email, password)){
                // Ova linija te prebacuje na ProductsView
                System.out.println("Logged in");
                getUI().ifPresent(ui -> ui.navigate(ArticlesView.class));
            }

            //TODO: Error handling if login doesnt pass

        });

        // velika kutija: content - u nju ubacujemo malu kutiju 1 (branding) i malu kutiju 2 (forma) - horizontal
		HorizontalLayout content = new HorizontalLayout(branding, loginForm);
        content.setAlignItems(Alignment.CENTER);
        content.setSpacing(true);

        // "lepimo" veliku kutiju (content) za prozor (LoginView)
        add(content);
	}

}
