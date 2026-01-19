package sitprojekat.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@CssImport("./style/style.css")
@Route(value = "UserProfile",layout = HeaderAndNavBar.class)
public class UserProfileView extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7812594761038414434L;

	public UserProfileView() {
		
		Icon backArrowIcon=VaadinIcon.ARROW_BACKWARD.create();
		Button backButton=new Button("Povratak",backArrowIcon);
		backButton.addClassName("brownButton");
		
		H2 titleH2=new H2();
		titleH2.setText("Profil");
		titleH2.addClassName("whiteText");
		
		Icon userIcon=VaadinIcon.USER.create();
		userIcon.addClassName("icon");
		EmailField emailField = new EmailField();
		emailField.setPlaceholder("Email");
		emailField.setPrefixComponent(userIcon);
		emailField.addClassName("inputField");
		
		
		Icon homeIcon=VaadinIcon.HOME_O.create();
		homeIcon.addClassName("icon");
		TextField addressTextField = new TextField();
		addressTextField.setPlaceholder("Adresa (Opciono)");
		addressTextField.setPrefixComponent(homeIcon);
		addressTextField.addClassName("inputField");
		
		Icon phoneIcon=VaadinIcon.PHONE.create();
		phoneIcon.addClassName("icon");
		TextField telephoneTextField = new TextField();
		telephoneTextField.setPlaceholder("Broj Telefona (Opciono)");
		telephoneTextField.setPrefixComponent(phoneIcon);
		telephoneTextField.addClassName("inputField");
		
		Icon keyIconPassword=VaadinIcon.KEY_O.create();
		keyIconPassword.addClassName("icon");
		PasswordField passwordPasswordField = new PasswordField();
		passwordPasswordField.setPlaceholder("Lozinka");
		passwordPasswordField.setPrefixComponent(keyIconPassword);
		passwordPasswordField.addClassName("inputField");
		
		Icon keyIconPasswordCheck=VaadinIcon.KEY_O.create();
		keyIconPasswordCheck.addClassName("icon");
		PasswordField confirmPasswordPasswordField = new PasswordField();
		confirmPasswordPasswordField.setPlaceholder("Potvrdi Lozinku");
		confirmPasswordPasswordField.setPrefixComponent(keyIconPasswordCheck);
		confirmPasswordPasswordField.addClassName("inputField");
		
		Button saveChangesButton =new Button();
		saveChangesButton.setText("Sacuvaj Izmene");
		saveChangesButton.addClassName("saveChangesButton");

		
		Button logOutButton =new Button();
		logOutButton.setText("Odjavi se");
		logOutButton.addClassName("logOutButton");
		
		VerticalLayout userProfileContainer = new VerticalLayout(); 
			
		userProfileContainer.add(emailField,addressTextField,telephoneTextField,passwordPasswordField,confirmPasswordPasswordField,saveChangesButton,logOutButton);
		
		userProfileContainer.addClassName("userProfileContainer");

		
		VerticalLayout formContainer=new VerticalLayout();
		formContainer.add(titleH2,userProfileContainer);
		formContainer.setAlignItems(Alignment.CENTER);
		formContainer.setJustifyContentMode(JustifyContentMode.CENTER);
		
		add(backButton,formContainer);
	}

	
	
	

}
