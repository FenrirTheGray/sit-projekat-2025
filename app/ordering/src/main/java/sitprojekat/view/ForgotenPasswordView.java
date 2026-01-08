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
		emailField.getStyle().set("margin-top", "40px");
		emailField.getStyle().set("margin-bottom", "20px");
		
		VerticalLayout loginContainer = new VerticalLayout(); 
		
		
		Button loginButton =new Button();
		loginButton.setText("Restore password");
		loginButton.getStyle().set("background-color", "#3F220F");
		loginButton.getStyle().set("color", "#ffffff");
		loginButton.getStyle().set("border-radius", "8px");
		loginButton.getStyle().set("margin-bottom", "20px");
		
		Span loginSpan=new Span();
		loginSpan.setText("Prijavite se");
		loginSpan.getStyle().set("text-align", "center");
		loginSpan.getStyle().set("text-decoration", "underline");
		loginSpan.getStyle().set("margin-bottom", "20px");
		loginSpan.getStyle().set("margin-bottom", "20px");
		
		
		loginContainer.add(emailField,loginButton,loginSpan);
		
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
