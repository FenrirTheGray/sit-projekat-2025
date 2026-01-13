package rs.ac.singidunu.cms.view;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Style.Overflow;
import com.vaadin.flow.dom.Style.TextAlign;
import com.vaadin.flow.router.Route;

@Route(value = "") // početna stranica
@CssImport("./style/style.css")
public class LoginView extends VerticalLayout {

	private static final long serialVersionUID = 6313156717813295316L;

	public LoginView() {
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
        // TODO: treba izmeniti i srediti u budućnosti kad dodamo logovanje
		loginForm.addLoginListener(event -> {
            String email = event.getUsername();
            // String password = event.getPassword();
            
            System.out.println("email: " + email.toLowerCase()); // testiramo ispis emaila u konzolu u IDE

            // Ova linija te prebacuje na ProductsView
            getUI().ifPresent(ui -> ui.navigate(ProductsView.class));
        });

        // velika kutija: content - u nju ubacujemo malu kutiju 1 (branding) i malu kutiju 2 (forma) - horizontal
		HorizontalLayout content = new HorizontalLayout(branding, loginForm);
        content.setAlignItems(Alignment.CENTER);
        content.setSpacing(true);

        // "lepimo" veliku kutiju (content) za prozor (LoginView)
        add(content);
	}
	
	
	
	

	
}
