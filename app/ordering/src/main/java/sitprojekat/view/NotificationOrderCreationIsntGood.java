package sitprojekat.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;

@StyleSheet("https://fonts.googleapis.com/css?family=Kaushan+Script") 
@CssImport("./style/style.css")
public class NotificationOrderCreationIsntGood extends Dialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2267375315826572775L;

	public NotificationOrderCreationIsntGood() {
		setWidth("850px");
		setHeight("550px");
		addClassName("notif");
		
		VerticalLayout dialogContainer = new VerticalLayout();
		dialogContainer.setAlignItems(Alignment.CENTER);
		dialogContainer.getStyle().set("color", "#ffffff");
		dialogContainer.setSizeFull();
		
		
		H2 titleH2=new H2();
		titleH2.setText("Potvrda");
		titleH2.getStyle().set("font-family", "'Kaushan Script");
		titleH2.getStyle().set("font-size", "64px");
		titleH2.getStyle().set("color", "#ffffff");
		
		VerticalLayout informationContainer = new VerticalLayout();
		
		Span textSpan=new Span();
		textSpan.setText("Uneti podaci za porudzbinu nisu validni");
	    textSpan.getStyle().set("text-align", "center"); 
	    textSpan.getStyle().set("color","#000000");
	    textSpan.setWidthFull();
		
		informationContainer.setMaxWidth("350px"); 
		informationContainer.getStyle().set("background-color", "#c9ab71");
		informationContainer.getStyle().set("padding", "20px");
		informationContainer.getStyle().set("border-radius", "8px");
		informationContainer.setPadding(true);
		informationContainer.setAlignItems(Alignment.CENTER);
		informationContainer.setHeight("250px");
		
		Button okButton=new Button();//#74914c
		okButton.setText("OK");
		okButton.getStyle().set("color","#ffffff");
		okButton.getStyle().set("background-color","#74914c");
		okButton.setWidth("80px");
		okButton.addClickListener(e->close());
		
		informationContainer.add(textSpan,okButton);
		dialogContainer.add(titleH2,informationContainer);
		
		
		
		
		add(dialogContainer);
	}
	
}
