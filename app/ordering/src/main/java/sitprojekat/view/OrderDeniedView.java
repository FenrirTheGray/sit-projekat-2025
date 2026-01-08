package sitprojekat.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.router.Route;

@Route(value = "OrderDenied",layout = HeaderAndNavBar.class)
public class OrderDeniedView extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1185279999190547056L;

	public OrderDeniedView() {
		Icon backArrowIcon=VaadinIcon.ARROW_BACKWARD.create();
		Button buttonBack=new Button("Povratak",backArrowIcon);
		buttonBack.addClassName("dugmeNazad");
		
		H2 titleH2=new H2();
		titleH2.setText("Porudzbina Nije Primljena");
		titleH2.setWidthFull();
		titleH2.getStyle().set("text-align", "center");
		
		Span orderIDSpan=new Span();
		orderIDSpan.setText("Doslo je do greske:");
		
		VerticalLayout orderingContainer = new VerticalLayout(); 

		Icon retryIcon=VaadinIcon.REFRESH.create();
		Button retryButton=new Button("Pokusaj ponovo",retryIcon);
		retryButton.addClassName("dugmeNazad");
		retryButton.addClickListener(e->{
			
			//NotificationAddToCardNotValidView notif=new NotificationAddToCardNotValidView();
			//ChoiceConfirmationDeleteFromCart notif=new ChoiceConfirmationDeleteFromCart();
			//NotificationConfirmationDeletingFromCartNotValid notif=new NotificationConfirmationDeletingFromCartNotValid();
			//NotificationOrderCreationIsntGood notif=new NotificationOrderCreationIsntGood();
			NotificationCardDoesntHaveEnoughFunds notif=new NotificationCardDoesntHaveEnoughFunds();
			notif.open();
		});
		
		
		orderingContainer.add(titleH2,orderIDSpan,retryButton,buttonBack);
		
		orderingContainer.setMaxWidth("350px"); 
		orderingContainer.getStyle().set("background-color", "#c9ab71");
		orderingContainer.getStyle().set("padding", "20px");
		orderingContainer.getStyle().set("border-radius", "8px");
		orderingContainer.setPadding(true);
		orderingContainer.setAlignItems(Alignment.STRETCH);
		
		VerticalLayout orderContainer=new VerticalLayout();
		orderContainer.add(orderingContainer);
		orderContainer.setAlignItems(Alignment.CENTER);
		orderContainer.setJustifyContentMode(JustifyContentMode.CENTER);
		
		add(orderContainer);
		}
}
