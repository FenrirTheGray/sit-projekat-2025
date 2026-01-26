package sitprojekat.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import sitprojekat.interfajsi.OrderDeniedViewInterface;
import sitprojekat.presenter.OrderDeniedPresenter;

@Route(value = "OrderDenied",layout = HeaderAndNavBar.class)
public class OrderDeniedView extends VerticalLayout implements OrderDeniedViewInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1185279999190547056L;

	private Icon backArrowIcon=VaadinIcon.ARROW_BACKWARD.create();
	private Button backButton=new Button("Povratak",backArrowIcon);
	private H2 titleH2=new H2();
	private Span orderIDSpan=new Span();
	private Icon retryIcon=VaadinIcon.REFRESH.create();
	private Button retryButton=new Button("Pokusaj ponovo",retryIcon);
	private final OrderDeniedPresenter presenter;
	public OrderDeniedView(OrderDeniedPresenter presenter) {
		this.presenter=presenter;
		presenter.setView(this);
		
		backButton.addClassName("brownButton");
		backButton.addClickListener(e->presenter.backClick());
	
		titleH2.setText("Porudzbina Nije Primljena");
		titleH2.addClassName("centeredText");
		
		
		orderIDSpan.setText("Doslo je do greske:");
		
		VerticalLayout orderingContainer = new VerticalLayout(); 

		
		retryButton.addClassName("brownButton");
		retryButton.addClickListener(e->presenter.retry());
		
		
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
