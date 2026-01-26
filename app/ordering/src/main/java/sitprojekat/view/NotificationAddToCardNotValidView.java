package sitprojekat.view;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@StyleSheet("https://fonts.googleapis.com/css?family=Kaushan+Script") 
@CssImport("./style/style.css")
public class NotificationAddToCardNotValidView extends Dialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6128634575269601304L;

	
	
	public NotificationAddToCardNotValidView() {
		setWidth("850px");
		setHeight("550px");
		addClassName("notif");
		
		VerticalLayout dialogContainer = new VerticalLayout();
		dialogContainer.setAlignItems(Alignment.CENTER);
		dialogContainer.addClassName("whiteText");
		
		
		H2 titleH2=new H2();
		titleH2.setText("Potvrda");
		titleH2.addClassName("whiteTextNotif");
		
		VerticalLayout informationContainer = new VerticalLayout();
		
		Span textSpan=new Span();
		textSpan.setText("Izabrani proizvod nije na stanju");
		textSpan.addClassName("notifText");
		
		informationContainer.addClassName("informationContainer");
		informationContainer.setPadding(true);
		informationContainer.setAlignItems(Alignment.CENTER);
		
		Button okButton=new Button();
		okButton.setText("OK");
		okButton.addClassName("greenButton");
		okButton.addClickListener(e->close());
		
		informationContainer.add(textSpan,okButton);
		dialogContainer.add(titleH2,informationContainer);
		
		
		
		
		add(dialogContainer);
	}

	
	
}
