package sitprojekat.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.router.Route;

import sitprojekat.presenter.ForgotenPasswordPresenter;

@CssImport("./style/style.css")
@StyleSheet("https://fonts.googleapis.com/css?family=Kaushan+Script")
@Route("ForgotenPassword")
public class ForgotenPasswordView extends VerticalLayout{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6425992396889072675L;

	private EmailField emailField = new EmailField();
	private Button restorePasswordButton =new Button();
	private Span messageSpan = new Span();
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
		
		
		
		restorePasswordButton.setText("Posalji link");
		restorePasswordButton.addClassName("brownButton");
		restorePasswordButton.addClickListener(e->presenter.getAccountPassword());

		messageSpan.setVisible(false);

		Span loginSpan=new Span();
		loginSpan.setText("Prijavite se");
		loginSpan.addClassName("loginSpan");

		loginSpan.addClickListener(e->presenter.loginScreen());

		forgotenPasswordContainer.add(emailField,restorePasswordButton,messageSpan,loginSpan);
		
		forgotenPasswordContainer.addClassName("orderingContainer");
		forgotenPasswordContainer.setPadding(true);
		forgotenPasswordContainer.setAlignItems(Alignment.STRETCH);
		
		VerticalLayout orderContainer=new VerticalLayout();
		orderContainer.add(titleH2,forgotenPasswordContainer);
		orderContainer.setAlignItems(Alignment.CENTER);
		orderContainer.setJustifyContentMode(JustifyContentMode.CENTER);
		
		add(orderContainer);
	}
	public EmailField getEmailField() {
		return emailField;
	}

	public void showMessage(String message, boolean isError) {
		messageSpan.setText(message);
		messageSpan.setVisible(true);
		messageSpan.getStyle().set("color", isError ? "#c62828" : "#2e7d32");
		messageSpan.getStyle().set("text-align", "center");
	}

	public void clearForm() {
		emailField.clear();
	}

}
