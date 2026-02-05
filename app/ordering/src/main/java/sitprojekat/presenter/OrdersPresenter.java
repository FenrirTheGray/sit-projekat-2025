package sitprojekat.presenter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.spring.annotation.UIScope;

import sitprojekat.model.Order;
import sitprojekat.service.OrderService;
import sitprojekat.view.OrdersView;

@Component
@UIScope
public class OrdersPresenter {

	private OrdersView view;
	private OrderService service;
	private OrderPresenter orderPresenter;
	public OrdersPresenter(OrderService service,OrderPresenter orderPresenter) {
		this.service=service;
		this.orderPresenter=orderPresenter;
	}

	public void setView(OrdersView view) {
		this.view = view;

	}

	public void getOrders() {
	
		List<Order> listaOrders=service.getOrders();		
		if(listaOrders!=null) {
		for (Order o : listaOrders) {
			
			view.getOrderedProductsContainer().add(view.createOrdersContainer(o));

		}

		view.getOrderedProductsContainer().setAlignItems(Alignment.CENTER);
		view.getOrderedProductsContainer().setJustifyContentMode(JustifyContentMode.CENTER);
		}

	}

	public void findById1() {

		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd MM yyyy");
		DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");

		LocalDate dateCreated = LocalDate.parse("2025-12-21");
		String formattedDdateCreated = dateCreated.format(formatterDate); // lespi format za datum

		LocalTime timeCreated = LocalTime.parse("16:30");
		LocalTime timeSent = LocalTime.parse("16:30");
		LocalTime timeRecieved = LocalTime.parse("16:30");

		Order order = new Order();

		List<Order> listaOrders;

		listaOrders = List.of(
				new Order(),
				new Order(),
				new Order(),
				new Order());

		for (Order o : listaOrders) {

			view.getOrderedProductsContainer().add(view.createOrdersContainer(o));

		}

		view.getOrderedProductsContainer().setAlignItems(Alignment.CENTER);
		view.getOrderedProductsContainer().setJustifyContentMode(JustifyContentMode.CENTER);

	}

	public void OrderDetailsScreen(String id) {
		orderPresenter.setOrder(id);
		UI.getCurrent().navigate("Order");
	}

}
