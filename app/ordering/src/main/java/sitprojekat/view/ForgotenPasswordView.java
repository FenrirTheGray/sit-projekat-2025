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
import com.vaadin.flow.router.Route;

@StyleSheet("https://fonts.googleapis.com/css?family=Kaushan+Script")        
@Route("ForgotenPassword")
public class ForgotenPasswordView extends VerticalLayout{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6425992396889072675L;

	public ForgotenPasswordView() {
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
		emailField.addClassName("emailField");
		
		VerticalLayout forgotenPasswordContainer = new VerticalLayout(); 
		
		
		Button restorePasswordButton =new Button();
		restorePasswordButton.setText("Restore password");
		restorePasswordButton.addClassName("brownButton");
		
		Span loginSpan=new Span();
		loginSpan.setText("Prijavite se");
		loginSpan.addClassName("loginSpan");
		
		loginSpan.addClickListener(e->{UI.getCurrent().navigate("LoginScreen");});
		
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
}
