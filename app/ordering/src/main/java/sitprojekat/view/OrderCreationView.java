package sitprojekat.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value = "OrderCreation",layout = HeaderAndNavBar.class)
public class OrderCreationView extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8829602058718508863L;

	private String choice="";
	public OrderCreationView() {
		
	
	Icon backArrowIcon=VaadinIcon.ARROW_BACKWARD.create();
	Button backButton=new Button("Povratak",backArrowIcon);
	backButton.addClassName("dugmeNazad");
	
	H2 titleH2=new H2();
	titleH2.setText("Ukupna Cena :"+1+" RSD");
	titleH2.getStyle().set("color", "#ffffff");
	
	Icon homeIcon=VaadinIcon.HOME_O.create();
	homeIcon.setColor("#ffffff");
	TextField addressTextField = new TextField();
	addressTextField.setPrefixComponent(homeIcon);
	addressTextField.setPlaceholder("Adresa");
	addressTextField.getStyle().set("background-color", "#204824");
	addressTextField.getStyle().set("color", "#ffffff");
	addressTextField.getStyle().set("border-radius", "8px");
	
	Icon phoneIcon=VaadinIcon.PHONE.create();
	phoneIcon.setColor("#ffffff");
	TextField telephoneTextField = new TextField();
	telephoneTextField.setPrefixComponent(phoneIcon);
	telephoneTextField.setPlaceholder("Broj telefona");
	telephoneTextField.getStyle().set("background-color", "#204824");
	telephoneTextField.getStyle().set("color", "#ffffff");
	telephoneTextField.getStyle().set("border-radius", "8px");

	Span payingOptionSpan=new Span();
	payingOptionSpan.setText("Vrsta Placanja:");
	payingOptionSpan.setWidthFull();
	payingOptionSpan.getStyle().set("text-align", "center");
	
	
	
	Button cardChoiceButton =new Button();
	cardChoiceButton.setText("Kartica");
	cardChoiceButton.getStyle().set("background-color", "#3F220F");
	cardChoiceButton.getStyle().set("color", "#ffffff");
	cardChoiceButton.getStyle().set("border-radius", "8px");
	
	Button cashChoiceButton =new Button();
	cashChoiceButton.setText("Gotovina");
	cashChoiceButton.getStyle().set("background-color", "#3F220F");//#74914c
	cashChoiceButton.getStyle().set("color", "#ffffff");
	cashChoiceButton.getStyle().set("border-radius", "8px");
	
	cardChoiceButton.addClickListener(e->{
		choice="card";
		cashChoiceButton.getStyle().set("background-color", "#3F220F");
		cardChoiceButton.getStyle().set("background-color", "#74914c");
	});
	
	cashChoiceButton.addClickListener(e->{
		choice="cash";
		cardChoiceButton.getStyle().set("background-color", "#3F220F");
		cashChoiceButton.getStyle().set("background-color", "#74914c");
	});
	
	
	VerticalLayout orderingContainer = new VerticalLayout(); 
	
	HorizontalLayout payingOptionChoiceContainer=new HorizontalLayout();
	payingOptionChoiceContainer.add(cardChoiceButton,cashChoiceButton);
	payingOptionChoiceContainer.setJustifyContentMode(JustifyContentMode.CENTER);
	payingOptionChoiceContainer.getStyle().set("gap", "45px");
	
	Button createOrderButton =new Button();
	createOrderButton.setText("Kreiraj Porudzbinu");
	createOrderButton.getStyle().set("background-color", "#3F220F");
	createOrderButton.getStyle().set("color", "#ffffff");
	createOrderButton.getStyle().set("border-radius", "8px");
	
	Span disclamerSpan=new Span();
	disclamerSpan.setText("Placanje karticom se obavlja na Third Party website-u");
	disclamerSpan.getStyle().set("text-align", "center");
	
	orderingContainer.add(addressTextField,telephoneTextField,payingOptionSpan,payingOptionChoiceContainer,createOrderButton,disclamerSpan);
	
	orderingContainer.setMaxWidth("350px"); 
	orderingContainer.getStyle().set("background-color", "#c9ab71");
	orderingContainer.getStyle().set("padding", "20px");
	orderingContainer.getStyle().set("border-radius", "8px");
	orderingContainer.setPadding(true);
	orderingContainer.setAlignItems(Alignment.STRETCH);
	
	VerticalLayout orderContainer=new VerticalLayout();
	orderContainer.add(titleH2,orderingContainer);
	orderContainer.setAlignItems(Alignment.CENTER);
	orderContainer.setJustifyContentMode(JustifyContentMode.CENTER);
	
	add(backButton,orderContainer);
	}
}
