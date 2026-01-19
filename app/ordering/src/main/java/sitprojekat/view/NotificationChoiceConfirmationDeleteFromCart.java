package sitprojekat.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;

@StyleSheet("https://fonts.googleapis.com/css?family=Kaushan+Script")
@CssImport("./style/style.css")
public class NotificationChoiceConfirmationDeleteFromCart extends Dialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8757622649524542547L;

	public NotificationChoiceConfirmationDeleteFromCart() {
		setWidth("850px");
		setHeight("550px");
		addClassName("notif");

		VerticalLayout dialogContainer = new VerticalLayout();
		dialogContainer.setAlignItems(Alignment.CENTER);
		dialogContainer.addClassName("whiteText");

		H2 titleH2 = new H2();
		titleH2.setText("Potvrda");
		titleH2.addClassName("whiteTextNotif");

		VerticalLayout informationContainer = new VerticalLayout();

		Span textSpan = new Span();
		textSpan.setText("Da li zelite da obrisete taj artikal iz korpe");
		textSpan.addClassName("notifText");

		informationContainer.addClassName("informationContainer");
		informationContainer.setPadding(true);
		informationContainer.setAlignItems(Alignment.CENTER);

		Button YesButton = new Button();
		YesButton.setText("Da");
		YesButton.addClassName("greenButton");
		YesButton.addClickListener(e -> close());
		
		Button NoButton = new Button();
		NoButton.setText("Ne");
		NoButton.addClassName("brownButton");
		NoButton.addClickListener(e -> close());

		HorizontalLayout removeChoiceContainer=new HorizontalLayout();
		
		removeChoiceContainer.add(YesButton,NoButton);
		informationContainer.add(textSpan, removeChoiceContainer);
		dialogContainer.add(titleH2, informationContainer);

		add(dialogContainer);
	}
}
