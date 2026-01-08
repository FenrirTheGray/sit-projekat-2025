package sitprojekat.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
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
public class UserProfile extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7812594761038414434L;

	public UserProfile() {
		
		Icon backArrowIcon=VaadinIcon.ARROW_BACKWARD.create();
		Button buttonBack=new Button("Povratak",backArrowIcon);
		buttonBack.addClassName("dugmeNazad");
		
		H2 titleH2=new H2();
		titleH2.setText("Profil");
		titleH2.getStyle().set("color", "#ffffff");
		
		Icon userIcon=VaadinIcon.USER.create();
		userIcon.setColor("#ffffff");
		EmailField emailField = new EmailField();
		emailField.setPlaceholder("Email");
		emailField.setPrefixComponent(userIcon);
		emailField.getStyle().set("background-color", "#204824");
		emailField.getStyle().set("color", "#ffffff");
		emailField.getStyle().set("border-radius", "8px");
		
		
		Icon homeIcon=VaadinIcon.HOME_O.create();
		homeIcon.setColor("#ffffff");
		TextField addressTextField = new TextField();
		addressTextField.setPlaceholder("Adresa (Opciono)");
		addressTextField.setPrefixComponent(homeIcon);
		addressTextField.getStyle().set("background-color", "#204824");
		addressTextField.getStyle().set("color", "#ffffff");
		addressTextField.getStyle().set("border-radius", "8px");
		
		Icon phoneIcon=VaadinIcon.PHONE.create();
		phoneIcon.setColor("#ffffff");
		TextField telephoneTextField = new TextField();
		telephoneTextField.setPlaceholder("Broj Telefona (Opciono)");
		telephoneTextField.setPrefixComponent(phoneIcon);
		telephoneTextField.getStyle().set("background-color", "#204824");
		telephoneTextField.getStyle().set("color", "#ffffff");
		telephoneTextField.getStyle().set("border-radius", "8px");
		
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
		
		Button saveChangesButton =new Button();
		saveChangesButton.setText("Sacuvaj Izmene");
		saveChangesButton.getStyle().set("background-color", "#3F220F");
		saveChangesButton.getStyle().set("color", "#ffffff");
		saveChangesButton.getStyle().set("border-radius", "8px");
		
		Button logOutButton =new Button();
		logOutButton.setText("Odjavi se");
		logOutButton.getStyle().set("background-color", "#910707");
		logOutButton.getStyle().set("color", "#ffffff");
		logOutButton.getStyle().set("border-radius", "8px");

		VerticalLayout UserProfileContainer = new VerticalLayout(); 
		
		
		
		UserProfileContainer.add(emailField,addressTextField,telephoneTextField,passwordPasswordField,confirmPasswordPasswordField,saveChangesButton,logOutButton);
		
		UserProfileContainer.setMaxWidth("350px"); 
		UserProfileContainer.getStyle().set("background-color", "#c9ab71");
		UserProfileContainer.getStyle().set("padding", "20px");
		UserProfileContainer.getStyle().set("border-radius", "8px");
		UserProfileContainer.setPadding(true);
		UserProfileContainer.setAlignItems(Alignment.STRETCH);
		
		VerticalLayout formContainer=new VerticalLayout();
		formContainer.add(titleH2,UserProfileContainer);
		formContainer.setAlignItems(Alignment.CENTER);
		formContainer.setJustifyContentMode(JustifyContentMode.CENTER);
		
		add(buttonBack,formContainer);
	}

	
	
	

}
