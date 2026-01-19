package sitprojekat.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.Route;

@StyleSheet("https://fonts.googleapis.com/css?family=Kaushan+Script")        
@Route("LoginScreen")
public class LoginScreenView extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3461984050180148438L;

	public LoginScreenView() {
		
		addClassName("greenBackground");
		setSizeFull();
		
		H2 titleH2=new H2();
		titleH2.setText("Ordering app");
		titleH2.addClassName("whiteTextNotif");
		
		Icon userIcon=VaadinIcon.USER.create();
		userIcon.addClassName("icon");
		EmailField emailField = new EmailField();
		emailField.setPlaceholder("Email");
		emailField.setPrefixComponent(userIcon);
		emailField.addClassName("inputField");
		
		Icon keyIconPassword=VaadinIcon.KEY_O.create();
		keyIconPassword.addClassName("icon");
		PasswordField passwordPasswordField = new PasswordField();
		passwordPasswordField.setPlaceholder("Lozinka");
		passwordPasswordField.setPrefixComponent(keyIconPassword);
		passwordPasswordField.addClassName("inputField");		
		
		VerticalLayout loginContainer = new VerticalLayout(); 
				
		Button loginButton =new Button();
		loginButton.setText("Login");
		loginButton.addClassName("loginButton");
		
		Span forgotenPasswordSpan=new Span();
		forgotenPasswordSpan.setText("Zaboravili ste lozinku?");
		forgotenPasswordSpan.addClassName("underlineText");
		
		forgotenPasswordSpan.addClickListener(e->{UI.getCurrent().navigate("ForgotenPassword");});
		
		Span dontHaveAnAccountSpan=new Span();
		dontHaveAnAccountSpan.setText("Nemate nalog?");
		dontHaveAnAccountSpan.addClassName("underlineText2");
		
		dontHaveAnAccountSpan.addClickListener(e->{UI.getCurrent().navigate("AccountCreation");});
		
		loginContainer.add(emailField,passwordPasswordField,loginButton,forgotenPasswordSpan,dontHaveAnAccountSpan);
		
		loginContainer.setMaxWidth("350px"); 
		loginContainer.getStyle().set("background-color", "#c9ab71");
		loginContainer.getStyle().set("padding", "20px");
		loginContainer.getStyle().set("border-radius", "8px");
		loginContainer.setPadding(true);
		loginContainer.setAlignItems(Alignment.STRETCH);
		
		VerticalLayout orderContainer=new VerticalLayout();
		orderContainer.add(titleH2,loginContainer);
		orderContainer.setAlignItems(Alignment.CENTER);
		orderContainer.setJustifyContentMode(JustifyContentMode.CENTER);
		
		add(orderContainer);
	}
}
