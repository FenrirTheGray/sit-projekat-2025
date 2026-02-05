package sitprojekat.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.router.Route;

import sitprojekat.interfaces.ForgotenPasswordViewInterface;
import sitprojekat.presenter.ForgotenPasswordPresenter;

@StyleSheet("https://fonts.googleapis.com/css?family=Kaushan+Script")        
@Route("ForgotenPassword")
public class ForgotenPasswordView extends VerticalLayout implements ForgotenPasswordViewInterface{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6425992396889072675L;

	private EmailField emailField = new EmailField();
	private Button restorePasswordButton =new Button();
	private ForgotenPasswordPresenter presenter;
	public ForgotenPasswordView(ForgotenPasswordPresenter presenter) {
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
		emailField.addClassName("emailField");
		
		VerticalLayout forgotenPasswordContainer = new VerticalLayout(); 
		
		
		
		restorePasswordButton.setText("Restore password");
		restorePasswordButton.addClassName("brownButton");
		restorePasswordButton.addClickListener(e->{
			
		if(!emailField.getValue().equals("")) {	
			presenter.forgotPassword(emailField.getValue());
		}
		else {
			Notification notification = Notification.show("email je prazan",3000, Notification.Position.BOTTOM_CENTER); // sta pise , koliko traje, pozicija
			notification.addThemeVariants(NotificationVariant.LUMO_ERROR);// koje je boje
		}
		});
		
		Span loginSpan=new Span();
		loginSpan.setText("Prijavite se");
		loginSpan.addClassName("loginSpan");
		
		loginSpan.addClickListener(e->presenter.loginScreen());
		
		forgotenPasswordContainer.add(emailField,restorePasswordButton,loginSpan);
		
		forgotenPasswordContainer.addClassName("orderingContainer");
		forgotenPasswordContainer.setPadding(true);
		forgotenPasswordContainer.setAlignItems(Alignment.STRETCH);
		
		VerticalLayout orderContainer=new VerticalLayout();
		orderContainer.add(titleH2,forgotenPasswordContainer);
		orderContainer.setAlignItems(Alignment.CENTER);
		orderContainer.setJustifyContentMode(JustifyContentMode.CENTER);
		
		add(orderContainer);
	}
	@Override
	public EmailField getEmailField() {
		return emailField;
	}
	
}
