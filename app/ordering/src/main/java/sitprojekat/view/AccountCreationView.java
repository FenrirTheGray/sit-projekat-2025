package sitprojekat.view;

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
		titleH2.getStyle().set("font-family", "'Kaushan Script");
		titleH2.getStyle().set("font-size", "75px");
		titleH2.getStyle().set("color", "#ffffff");
		
		Icon userIcon=VaadinIcon.USER.create();
		userIcon.setColor("#ffffff");
		EmailField emailField = new EmailField();
		emailField.setPlaceholder("Email");
		emailField.setPrefixComponent(userIcon);
		emailField.getStyle().set("background-color", "#204824");
		emailField.getStyle().set("color", "#ffffff");
		emailField.getStyle().set("border-radius", "8px");
		
		Icon keyIconPassword=VaadinIcon.KEY_O.create();
		keyIconPassword.setColor("#ffffff");
		PasswordField passwordPasswordField = new PasswordField();
		passwordPasswordField.setPlaceholder("Lozinka");
		passwordPasswordField.setPrefixComponent(keyIconPassword);
		passwordPasswordField.getStyle().set("background-color", "#204824");
		passwordPasswordField.getStyle().set("color", "#ffffff");
		passwordPasswordField.getStyle().set("border-radius", "8px");
		
		Icon keyIconPasswordCheck=VaadinIcon.KEY_O.create();
		keyIconPasswordCheck.setColor("#ffffff");
		PasswordField confirmPasswordPasswordField = new PasswordField();
		confirmPasswordPasswordField.setPlaceholder("Potvrdi Lozinka");
		confirmPasswordPasswordField.setPrefixComponent(keyIconPasswordCheck);
		confirmPasswordPasswordField.getStyle().set("background-color", "#204824");
		confirmPasswordPasswordField.getStyle().set("color", "#ffffff");
		confirmPasswordPasswordField.getStyle().set("border-radius", "8px");
		
		VerticalLayout loginContainer = new VerticalLayout(); 
			
		Button loginButton =new Button();
		loginButton.setText("Kreiraj nalog");
		loginButton.getStyle().set("background-color", "#3F220F");
		loginButton.getStyle().set("color", "#ffffff");
		loginButton.getStyle().set("border-radius", "8px");
		
		Span alreadyHaveAnAccountSpan=new Span();
		alreadyHaveAnAccountSpan.setText("Vec imate nalog?");
		alreadyHaveAnAccountSpan.getStyle().set("text-align", "center");
		alreadyHaveAnAccountSpan.getStyle().set("text-decoration", "underline");
		alreadyHaveAnAccountSpan.getStyle().set("margin-bottom", "20px");
		
		
		loginContainer.add(emailField,passwordPasswordField,confirmPasswordPasswordField,loginButton,alreadyHaveAnAccountSpan);
		
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
