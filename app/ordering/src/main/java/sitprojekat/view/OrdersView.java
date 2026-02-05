package sitprojekat.view;

import java.time.format.DateTimeFormatter;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import sitprojekat.interfaces.OrdersViewInterface;
import sitprojekat.model.Order;
import sitprojekat.presenter.OrdersPresenter;

@CssImport("./style/style.css")
@Route(value = "Orders", layout = HeaderAndNavBar.class)
public class OrdersView extends VerticalLayout  implements OrdersViewInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7881133440689089779L;

	private final OrdersPresenter presenter;
	
	VerticalLayout orderedProductsContainer = new VerticalLayout();
	public OrdersView(OrdersPresenter presenter) {
		this.presenter=presenter;
		presenter.setView(this);
		presenter.getOrders();
		
		
		
		
		add(orderedProductsContainer);                                           
		
	}

	public VerticalLayout createOrdersContainer(Order o) {

		VerticalLayout orderContainer = new VerticalLayout(); // glavni pravougaonik
		orderContainer.addClassName("orderContainer");

		orderContainer.setSpacing(false);

		HorizontalLayout topRowContainer = new HorizontalLayout(); // gornji red
		topRowContainer.setWidthFull();

		Span titleSpan = new Span("Porudzbina "+o.getId());
		titleSpan.addClassName("boldText2");

		topRowContainer.add(titleSpan);
		topRowContainer.expand(titleSpan);

		VerticalLayout dateTimeContainer = new VerticalLayout(); // spojeno datum i vreme u jedan
		dateTimeContainer.setSpacing(false);
		dateTimeContainer.setPadding(false);

		Span dateOrderedSpan = new Span();

		String[] createdAtDateTime=o.getCreatedAt().split(",");
		String createdAtDate=createdAtDateTime[0].trim();
		String createdAtTime=createdAtDateTime[1].trim();
		
		dateOrderedSpan.setText("Datum Porudzbine: "+createdAtDate+" Vreme Porudzbine: "+createdAtTime);
		dateOrderedSpan.addClassName("whiteText2");
	
		
		HorizontalLayout bottomRowContainer = new HorizontalLayout(); // donji red
		bottomRowContainer.setWidthFull();
		bottomRowContainer.setAlignItems(Alignment.CENTER);
		String SentAtTime;
		String RecievedtedAtTime;
		if(o.getSentAtTime()==null) {
			 SentAtTime="Nije jos poslato";
		}else {
			 SentAtTime="Poslato: "+o.getSentAtTime();
		}
		if(o.getSentAtTime()==null) {
			 RecievedtedAtTime="Nije jos uzeto";
		}else {
			 RecievedtedAtTime= "Uzeto: "+o.getRecievedtedAtTime();
		}
		
		Span timeOrderedSpan = new Span(SentAtTime+" "+RecievedtedAtTime);
		timeOrderedSpan.addClassName("whiteText");

		Button detailsButton = new Button("Detalji");
		detailsButton.addClassName("greenButton");
		detailsButton.addClickListener(e->presenter.OrderDetailsScreen(o.getId()));
		
		dateTimeContainer.add(dateOrderedSpan, timeOrderedSpan);

		bottomRowContainer.add(dateTimeContainer, detailsButton);
		bottomRowContainer.setJustifyContentMode(JustifyContentMode.BETWEEN);

		orderContainer.add(topRowContainer, bottomRowContainer);

		return orderContainer;
	}

	@Override
	public VerticalLayout getOrderedProductsContainer() {
		return this.orderedProductsContainer;
	}

	@Override
	public void setOrderedProductsContainer(VerticalLayout orderedProductsContainer) {
		this.orderedProductsContainer=orderedProductsContainer;
		
	}



	
}