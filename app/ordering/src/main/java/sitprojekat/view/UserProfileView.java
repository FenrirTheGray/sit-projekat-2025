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

import sitprojekat.interfaces.UserProfileViewInterface;
import sitprojekat.model.UserAccount;
import sitprojekat.presenter.UserProfilePresenter;
import sitprojekat.service.UserAccountService;

@CssImport("./style/style.css")
@Route(value = "UserProfile",layout = HeaderAndNavBar.class)
public class UserProfileView extends VerticalLayout implements UserProfileViewInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7812594761038414434L;

	private EmailField emailField = new EmailField();
	private TextField addressTextField = new TextField();
	private TextField telephoneTextField = new TextField();
	private PasswordField passwordPasswordField = new PasswordField();
	private PasswordField confirmPasswordPasswordField = new PasswordField();
	private final UserProfilePresenter presenter;
	private UserAccount user;
	public UserProfileView(UserProfilePresenter presenter) {
		this.presenter=presenter;
		presenter.setView(this);
		Icon backArrowIcon=VaadinIcon.ARROW_BACKWARD.create();
		Button backButton=new Button("Povratak",backArrowIcon);
		backButton.addClassName("brownButton");
		backButton.addClickListener(e->presenter.backClick());
		
		H2 titleH2=new H2();
		titleH2.setText("Profil");
		titleH2.addClassName("whiteText");
		
		Icon userIcon=VaadinIcon.USER.create();
		userIcon.addClassName("icon");
		
		emailField.setPlaceholder("Email");
		emailField.setPrefixComponent(userIcon);
		emailField.addClassName("inputField");
		
		
		Icon homeIcon=VaadinIcon.HOME_O.create();
		homeIcon.addClassName("icon");
		
		addressTextField.setPlaceholder("Adresa (Opciono)");
		addressTextField.setPrefixComponent(homeIcon);
		addressTextField.addClassName("inputField");
		
		Icon phoneIcon=VaadinIcon.PHONE.create();
		phoneIcon.addClassName("icon");
		
		telephoneTextField.setPlaceholder("Broj Telefona (Opciono)");
		telephoneTextField.setPrefixComponent(phoneIcon);
		telephoneTextField.addClassName("inputField");
		
		Icon keyIconPassword=VaadinIcon.KEY_O.create();
		keyIconPassword.addClassName("icon");
		
		passwordPasswordField.setPlaceholder("Lozinka");
		passwordPasswordField.setPrefixComponent(keyIconPassword);
		passwordPasswordField.addClassName("inputField");
		
		Icon keyIconPasswordCheck=VaadinIcon.KEY_O.create();
		keyIconPasswordCheck.addClassName("icon");
		
		confirmPasswordPasswordField.setPlaceholder("Potvrdi Lozinku");
		confirmPasswordPasswordField.setPrefixComponent(keyIconPasswordCheck);
		confirmPasswordPasswordField.addClassName("inputField");
		
		Button saveChangesButton =new Button();
		saveChangesButton.setText("Sacuvaj Izmene");
		saveChangesButton.addClassName("saveChangesButton");
		saveChangesButton.addClickListener(e->presenter.saveChanges());
		
		Button logOutButton =new Button();
		logOutButton.setText("Odjavi se");
		logOutButton.addClassName("logOutButton");
		logOutButton.addClickListener(e->presenter.LogOut());		
		VerticalLayout userProfileContainer = new VerticalLayout(); 
			
		userProfileContainer.add(emailField,addressTextField,telephoneTextField,passwordPasswordField,confirmPasswordPasswordField,saveChangesButton,logOutButton);
		
		userProfileContainer.addClassName("userProfileContainer");

		
		VerticalLayout formContainer=new VerticalLayout();
		formContainer.add(titleH2,userProfileContainer);
		formContainer.setAlignItems(Alignment.CENTER);
		formContainer.setJustifyContentMode(JustifyContentMode.CENTER);
		
		add(backButton,formContainer);
	}
	@Override
	public String getEmailField() {
		return emailField.getValue();
	}
	@Override
	public void setEmailField(EmailField email) {
		
		
	}
	@Override
	public String getAddressTextField() {
		return addressTextField.getValue();
	}
	@Override
	public void setAddressTextField(TextField address) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getTelephoneTextField() {
		return telephoneTextField.getValue();
	}
	@Override
	public void setTelephoneTextField(TextField telephone) {
		
		
	}
	@Override
	public String getPasswordPasswordField() {
		return passwordPasswordField.getValue();
	}
	@Override
	public void setPasswordPasswordField(PasswordField password) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getConfirmPasswordPasswordField() {
		return confirmPasswordPasswordField.getValue();
	}
	@Override
	public void setConfirmPasswordPasswordField(PasswordField confirmPassword) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
