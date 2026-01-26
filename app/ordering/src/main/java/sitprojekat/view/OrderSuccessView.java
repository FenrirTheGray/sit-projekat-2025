package sitprojekat.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import sitprojekat.interfajsi.OrderSuccessViewInterface;
import sitprojekat.presenter.OrderSuccessPresenter;

@Route(value = "OrderSuccess",layout = HeaderAndNavBar.class)
public class OrderSuccessView extends VerticalLayout implements OrderSuccessViewInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5932756931587031517L;

	private Icon backArrowIcon=VaadinIcon.ARROW_BACKWARD.create();
	private Button backButton=new Button("Povratak",backArrowIcon);
	private H2 titleH2=new H2();
	private Span orderIDSpan=new Span();
	private Span itemsOrderedSpan=new Span();
	private final OrderSuccessPresenter presenter;
	public OrderSuccessView(OrderSuccessPresenter presenter) {
		this.presenter=presenter;
		this.presenter.setView(this);
		
		backButton.addClassName("brownButton");
		backButton.addClickListener(e->presenter.mainScreen());
		
		titleH2.setText("Porudzbina Uspesno Primljena");
		titleH2.addClassName("centeredText");
		
		orderIDSpan.setText("Porudzbina ID:");
		
		VerticalLayout orderingContainer = new VerticalLayout(); 

		
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


