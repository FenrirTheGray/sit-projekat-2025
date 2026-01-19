package sitprojekat.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "OrderDenied",layout = HeaderAndNavBar.class)
public class OrderDeniedView extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1185279999190547056L;

	public OrderDeniedView() {
		Icon backArrowIcon=VaadinIcon.ARROW_BACKWARD.create();
		Button backButton=new Button("Povratak",backArrowIcon);
		backButton.addClassName("brownButton");
		
		H2 titleH2=new H2();
		titleH2.setText("Porudzbina Nije Primljena");
		titleH2.addClassName("centeredText");
		
		Span orderIDSpan=new Span();
		orderIDSpan.setText("Doslo je do greske:");
		
		VerticalLayout orderingContainer = new VerticalLayout(); 

		Icon retryIcon=VaadinIcon.REFRESH.create();
		Button retryButton=new Button("Pokusaj ponovo",retryIcon);
		retryButton.addClassName("brownButton");
		retryButton.addClickListener(e->{
			
			NotificationAddToCardNotValidView notif=new NotificationAddToCardNotValidView();
			notif.open();
		});
		
		
		orderingContainer.add(titleH2,orderIDSpan,retryButton,backButton);
		
		orderingContainer.addClassName("orderingContainer");
		orderingContainer.setPadding(true);
		orderingContainer.setAlignItems(Alignment.STRETCH);
		
		VerticalLayout orderContainer=new VerticalLayout();
		orderContainer.add(orderingContainer);
		orderContainer.setAlignItems(Alignment.CENTER);
		orderContainer.setJustifyContentMode(JustifyContentMode.CENTER);
		
		add(orderContainer);
		}
}
