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

@Route(value = "")
@CssImport("./style/style.css")
public class LoginView extends VerticalLayout {

	private static final long serialVersionUID = 6313156717813295316L;

	public LoginView() {
		getStyle().set("background-color", "#202020");
		getElement().getThemeList().add("dark");
		Icon computerIcon = VaadinIcon.DESKTOP.create();
		computerIcon.setSize("32px");
		computerIcon.getStyle().set("color", "white");
		H1 title = new H1("ServeLogic CMS");
		title.getStyle().setTextAlign(TextAlign.CENTER);
		VerticalLayout branding = new VerticalLayout(computerIcon, title);
		branding.setAlignItems(Alignment.CENTER);
		title.getStyle().setWidth("295px");
//		title.getStyle().setPadding("50px 150px");
		LoginForm loginForm = new LoginForm();
		loginForm.getElement().getThemeList().add("light dark");
		LoginI18n login = LoginI18n.createDefault();
        login.getForm().setUsername("Email");
        login.getForm().setPassword("Lozinka");
        login.getForm().setSubmit("Prijava");
        login.getForm().setForgotPassword("Zaboravljena lozinka?");
        login.getForm().setTitle("");
        
        loginForm.setI18n(login);
        loginForm.getElement().getClassList().add("damnedButton");
        loginForm.getElement().getStyle().setWidth("600px");
        loginForm.getStyle().setBorderRadius("15px");
        loginForm.getStyle().setOverflow(Overflow.HIDDEN);
        loginForm.getStyle().setBackgroundColor("#202020");
		
		loginForm.addLoginListener(event -> {
            String email = event.getUsername();
//            String password = event.getPassword();
            
            System.out.println("email: " + email.toLowerCase().toString());
        });
		
		HorizontalLayout content = new HorizontalLayout(branding, loginForm);
        content.setAlignItems(Alignment.CENTER);
        content.setSpacing(true);

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        add(content);
	}
	
	
	
	

	
}
