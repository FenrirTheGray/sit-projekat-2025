package sitprojekat.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.Route;

import sitprojekat.interfaces.AccountCreationViewInterface;
import sitprojekat.presenter.AccountCreationPresenter;

@CssImport("./style/style.css")
@StyleSheet("https://fonts.googleapis.com/css?family=Kaushan+Script")
@Route("AccountCreation")
public class AccountCreationView  extends VerticalLayout implements AccountCreationViewInterface{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2715309156192848608L;

	private EmailField emailField = new EmailField();
	private PasswordField passwordPasswordField = new PasswordField();
	private PasswordField confirmPasswordPasswordField = new PasswordField();
	private Button createAccountButton =new Button();
	private final AccountCreationPresenter presenter;
	
	public AccountCreationView(AccountCreationPresenter presenter) {
		this.presenter=presenter;
		this.presenter.setView(this);
		
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
		
		Icon keyIconPasswordCheck=VaadinIcon.KEY_O.create();
		keyIconPasswordCheck.addClassName("icon");
		
		confirmPasswordPasswordField.setPlaceholder("Potvrdi Lozinka");
		confirmPasswordPasswordField.setPrefixComponent(keyIconPasswordCheck);
		confirmPasswordPasswordField.addClassName("inputField");
		
		VerticalLayout accountCreationContainer = new VerticalLayout(); 
			
		
		createAccountButton.setText("Kreiraj nalog");
		createAccountButton.addClassName("brownButton");
		createAccountButton.addClickListener(e ->{
			
			if (!emailField.isEmpty()) {

				if (passwordPasswordField.getValue().equals(confirmPasswordPasswordField.getValue())) {
					presenter.createAccount(emailField.getValue(), confirmPasswordPasswordField.getValue());
				} else {
					Notification notification = Notification.show("lozinka mora biti ista",3000, Notification.Position.BOTTOM_CENTER); // sta pise , koliko traje, pozicija
					notification.addThemeVariants(NotificationVariant.LUMO_ERROR);// koje je boje
				}
			}else {
				Notification notification = Notification.show("email je prazan",3000, Notification.Position.BOTTOM_CENTER); // sta pise , koliko traje, pozicija
				notification.addThemeVariants(NotificationVariant.LUMO_ERROR);// koje je boje
			}
		});
		
		
		Span alreadyHaveAnAccountSpan=new Span();
		alreadyHaveAnAccountSpan.setText("Vec imate nalog?");
		alreadyHaveAnAccountSpan.addClassName("underlineText");
		
		alreadyHaveAnAccountSpan.addClickListener(e->presenter.loginScreen());
		
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

	@Override
	public EmailField getEmailField() {
		return emailField;
	}


	@Override
	public PasswordField getPasswordPasswordField() {
		return passwordPasswordField;
	}



	@Override
	public PasswordField getConfirmPasswordPasswordField() {
		return confirmPasswordPasswordField;
	}



	@Override
	public Button getCreateAccountButton() {
		return createAccountButton;
	}




	
}
