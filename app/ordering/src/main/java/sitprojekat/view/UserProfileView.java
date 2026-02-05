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

import sitprojekat.model.UserAccount;
import sitprojekat.presenter.UserProfilePresenter;

@CssImport("./style/style.css")
@Route(value = "UserProfile",layout = HeaderAndNavBar.class)
public class UserProfileView extends VerticalLayout{

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
	private VerticalLayout userProfileContainer = new VerticalLayout();
	private H2 titleH2=new H2();
	public UserProfileView(UserProfilePresenter presenter) {
		this.presenter=presenter;
		presenter.setView(this);
		Icon backArrowIcon=VaadinIcon.ARROW_BACKWARD.create();
		Button backButton=new Button("Povratak",backArrowIcon);
		backButton.addClassName("brownButton");
		backButton.addClickListener(e->presenter.backClick());
		
		
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
		logOutButton.addClickListener(e->{
			
		emailField.setValue("");	
		addressTextField.setValue("");
		telephoneTextField.setValue("");
		passwordPasswordField.setValue("");
		confirmPasswordPasswordField.setValue("");
		presenter.LogOut();
	});		
		 
			
		userProfileContainer.add(emailField,addressTextField,telephoneTextField,passwordPasswordField,confirmPasswordPasswordField,saveChangesButton,logOutButton);
		
		userProfileContainer.addClassName("userProfileContainer");

		userProfileContainer.setVisible(false);
		titleH2.setVisible(false);
		VerticalLayout formContainer=new VerticalLayout();
		formContainer.add(titleH2,userProfileContainer);
		formContainer.setAlignItems(Alignment.CENTER);
		formContainer.setJustifyContentMode(JustifyContentMode.CENTER);
		
		add(backButton,formContainer);
		presenter.updateView();
	}
	
	public String getEmailField() {
		return emailField.getValue();
	}
	
	public void setEmailField(String email) {
		emailField.setValue(email);
		
	}
	
	public String getAddressTextField() {
		return addressTextField.getValue();
	}
	
	public void setAddressTextField(String address) {
		this.addressTextField.setValue(address);
		
	}
	
	public String getTelephoneTextField() {
		return telephoneTextField.getValue();
	}
	
	public void setTelephoneTextField(String telephone) {
		this.telephoneTextField.setValue(telephone);
		
	}
	
	public String getPasswordPasswordField() {
		return passwordPasswordField.getValue();
	}
	
	public void setPasswordPasswordField(String password) {
		// TODO Auto-generated method stub
		
	}
	
	public String getConfirmPasswordPasswordField() {
		return confirmPasswordPasswordField.getValue();
	}
	
	public void setConfirmPasswordPasswordField(PasswordField confirmPassword) {
		// TODO Auto-generated method stub
		
	}
	
	public VerticalLayout getUserProfileContainer() {
		return userProfileContainer;
	}
	
	public void setUserProfileContainer(VerticalLayout userProfileContainer) {
		this.userProfileContainer = userProfileContainer;
	}
	
	public H2 getTitleH2() {
		return titleH2;
	}
	
	public void setTitleH2(H2 titleH2) {
		this.titleH2 = titleH2;
	}
	
	
	
	

}
