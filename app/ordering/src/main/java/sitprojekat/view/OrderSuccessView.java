package sitprojekat.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "OrderSuccess",layout = HeaderAndNavBar.class)
public class OrderSuccessView extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5932756931587031517L;

	public OrderSuccessView() {
		
		Icon backArrowIcon=VaadinIcon.ARROW_BACKWARD.create();
		Button backButton=new Button("Povratak",backArrowIcon);
		backButton.addClassName("brownButton");
		
		H2 titleH2=new H2();
		titleH2.setText("Porudzbina Uspesno Primljena");
		titleH2.addClassName("centeredText");
		
		Span orderIDSpan=new Span();
		orderIDSpan.setText("Porudzbina ID:");
		
		VerticalLayout orderingContainer = new VerticalLayout(); 

		Span itemsOrderedSpan=new Span();
		itemsOrderedSpan.setText("Pregled Proizvoda:");
		
		orderingContainer.add(titleH2,orderIDSpan,itemsOrderedSpan,backButton);
		
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

