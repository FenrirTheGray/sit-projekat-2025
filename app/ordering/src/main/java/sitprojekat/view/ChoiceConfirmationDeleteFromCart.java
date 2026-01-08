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
public class ChoiceConfirmationDeleteFromCart extends Dialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8757622649524542547L;

	public ChoiceConfirmationDeleteFromCart() {
		setWidth("850px");
		setHeight("550px");
		addClassName("notif");

		VerticalLayout dialogContainer = new VerticalLayout();
		dialogContainer.setAlignItems(Alignment.CENTER);
		dialogContainer.getStyle().set("color", "#ffffff");
		dialogContainer.setSizeFull();

		H2 titleH2 = new H2();
		titleH2.setText("Potvrda");
		titleH2.getStyle().set("font-family", "'Kaushan Script");
		titleH2.getStyle().set("font-size", "64px");
		titleH2.getStyle().set("color", "#ffffff");

		VerticalLayout informationContainer = new VerticalLayout();

		Span textSpan = new Span();
		textSpan.setText("Da li zelite da obrisete taj artikal iz korpe");
		textSpan.getStyle().set("text-align", "center");
		textSpan.getStyle().set("color", "#000000");
		textSpan.setWidthFull();

		informationContainer.setMaxWidth("350px");
		informationContainer.getStyle().set("background-color", "#c9ab71");
		informationContainer.getStyle().set("padding", "20px");
		informationContainer.getStyle().set("border-radius", "8px");
		informationContainer.setPadding(true);
		informationContainer.setAlignItems(Alignment.CENTER);
		informationContainer.setHeight("250px");

		Button YesButton = new Button();// #74914c
		YesButton.setText("Da");
		YesButton.getStyle().set("color", "#ffffff");
		YesButton.getStyle().set("background-color", "#74914c");
		YesButton.setWidth("80px");
		YesButton.addClickListener(e -> close());
		
		Button NoButton = new Button();// #74914c
		NoButton.setText("Ne");
		NoButton.getStyle().set("color", "#ffffff");
		NoButton.getStyle().set("background-color", "#3F220F");
		NoButton.setWidth("80px");
		NoButton.addClickListener(e -> close());

		HorizontalLayout removeChoiceContainer=new HorizontalLayout();
		
		removeChoiceContainer.add(YesButton,NoButton);
		informationContainer.add(textSpan, removeChoiceContainer);
		dialogContainer.add(titleH2, informationContainer);

		add(dialogContainer);
	}
}
