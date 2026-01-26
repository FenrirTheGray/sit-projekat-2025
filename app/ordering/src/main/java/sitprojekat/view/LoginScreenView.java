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

import sitprojekat.interfaces.LoginScreenViewInterface;
import sitprojekat.presenter.LoginScreenPresenter;

@StyleSheet("https://fonts.googleapis.com/css?family=Kaushan+Script")        
@Route("LoginScreen")
public class LoginScreenView extends VerticalLayout implements LoginScreenViewInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3461984050180148438L;
	private EmailField emailField = new EmailField();
	private PasswordField passwordPasswordField = new PasswordField();
	private Button loginButton =new Button();
	private final LoginScreenPresenter presenter;
	public LoginScreenView(LoginScreenPresenter presenter) {
		this.presenter=presenter;
		presenter.setView(this);
		
		addClassName("greenBackground");
		setSizeFull();
		
		H2 titleH2=new H2();
		titleH2.setText("Ordering app");
		titleH2.addClassName("whiteTextNotif");
		
		Icon userIcon=VaadinIcon.USER.create();
		userIcon.addClassName("icon");
		
		emailField.setPlaceholder("Email");
		emailField.setPrefixComponent(userIcon);
		emailField.addClassName("inputField");
		
		Icon keyIconPassword=VaadinIcon.KEY_O.create();
		keyIconPassword.addClassName("icon");
		
		passwordPasswordField.setPlaceholder("Lozinka");
		passwordPasswordField.setPrefixComponent(keyIconPassword);
		passwordPasswordField.addClassName("inputField");		
		
		VerticalLayout loginContainer = new VerticalLayout(); 
				
		
		loginButton.setText("Login");
		loginButton.addClassName("loginButton");
		loginButton.addClickListener(e->presenter.login());
		
		Span forgotenPasswordSpan=new Span();
		forgotenPasswordSpan.setText("Zaboravili ste lozinku?");
		forgotenPasswordSpan.addClassName("underlineText");
		
		forgotenPasswordSpan.addClickListener(e->presenter.forgotenPasswordScreen());
		
		Span dontHaveAnAccountSpan=new Span();
		dontHaveAnAccountSpan.setText("Nemate nalog?");
		dontHaveAnAccountSpan.addClassName("underlineText2");
		
		dontHaveAnAccountSpan.addClickListener(e->presenter.accountCreationScreen());
		
		loginContainer.add(emailField,passwordPasswordField,loginButton,forgotenPasswordSpan,dontHaveAnAccountSpan);

		loginContainer.setPadding(true);
		loginContainer.setAlignItems(Alignment.STRETCH);
		loginContainer.addClassName("orderingContainer");
		VerticalLayout orderContainer=new VerticalLayout();
		orderContainer.add(titleH2,loginContainer);
		orderContainer.setAlignItems(Alignment.CENTER);
		orderContainer.setJustifyContentMode(JustifyContentMode.CENTER);
		
		add(orderContainer);
	}
	@Override
	public EmailField getEmailField() {
		return emailField;
	}
	@Override
	public PasswordField getPasswordPasswordField() {
		return passwordPasswordField;
	}
	@Override
	public Button getLoginButton() {
		return loginButton;
	}

	
}
