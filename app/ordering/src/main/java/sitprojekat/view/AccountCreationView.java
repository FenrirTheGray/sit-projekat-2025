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
@Route("AccountCreation")
public class AccountCreationView  extends VerticalLayout{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2715309156192848608L;

	public AccountCreationView() {
		
		
		getStyle().set("background-color", "#204824");
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
		
		Icon keyIconPasswordCheck=VaadinIcon.KEY_O.create();
		keyIconPasswordCheck.addClassName("icon");
		PasswordField confirmPasswordPasswordField = new PasswordField();
		confirmPasswordPasswordField.setPlaceholder("Potvrdi Lozinka");
		confirmPasswordPasswordField.setPrefixComponent(keyIconPasswordCheck);
		confirmPasswordPasswordField.addClassName("inputField");
		
		VerticalLayout accountCreationContainer = new VerticalLayout(); 
			
		Button createAccountButton =new Button();
		createAccountButton.setText("Kreiraj nalog");
		createAccountButton.addClassName("brownButton");
		
		Span alreadyHaveAnAccountSpan=new Span();
		alreadyHaveAnAccountSpan.setText("Vec imate nalog?");
		alreadyHaveAnAccountSpan.addClassName("underlineText");
		
		alreadyHaveAnAccountSpan.addClickListener(e->{UI.getCurrent().navigate("LoginScreen");});
		
		accountCreationContainer.add(emailField,passwordPasswordField,confirmPasswordPasswordField,createAccountButton,alreadyHaveAnAccountSpan);
		
		accountCreationContainer.addClassName("orderingContainer");
		accountCreationContainer.setPadding(true);
		accountCreationContainer.setAlignItems(Alignment.STRETCH);
		
		VerticalLayout orderContainer=new VerticalLayout();
		orderContainer.add(titleH2,accountCreationContainer);
		orderContainer.setAlignItems(Alignment.CENTER);
		orderContainer.setJustifyContentMode(JustifyContentMode.CENTER);
		
		add(orderContainer);
	}
}
