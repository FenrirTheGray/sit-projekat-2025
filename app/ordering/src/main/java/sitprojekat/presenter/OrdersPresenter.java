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

import sitprojekat.interfaces.OrdersViewInterface;
import sitprojekat.model.Article;
import sitprojekat.model.Combo;
import sitprojekat.model.Order;
import sitprojekat.model.OrderStatus;
import sitprojekat.model.OrderedProduct;
import sitprojekat.model.UserAccount;
import sitprojekat.service.OrderService;

@Component
@UIScope
public class OrdersPresenter {

	private OrdersViewInterface view;
	private OrderService service;

	public OrdersPresenter(OrderService service) {
		this.service=service;
	}

	public void setView(OrdersViewInterface view) {
		this.view = view;

	}

	public void findById(String id) {
	
		List<Order> listaOrders=service.getOrders(id);		

		for (Order o : listaOrders) {

			view.getOrderedProductsContainer().add(view.createOrdersContainer(o));

		}

		view.getOrderedProductsContainer().setAlignItems(Alignment.CENTER);
		view.getOrderedProductsContainer().setJustifyContentMode(JustifyContentMode.CENTER);

	}

	public void findById1() {

		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd MM yyyy");
		DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");

		LocalDate dateCreated = LocalDate.parse("2025-12-21");
		String formattedDdateCreated = dateCreated.format(formatterDate); // lespi format za datum

		LocalTime timeCreated = LocalTime.parse("16:30");
		LocalTime timeSent = LocalTime.parse("16:30");
		LocalTime timeRecieved = LocalTime.parse("16:30");

		Order order = new Order("1", null, dateCreated, timeCreated, timeSent, timeRecieved, OrderStatus.CREATED, null,
				23, "kes");

		List<Order> listaOrders;

		listaOrders = List.of(
				new Order("1", null, dateCreated, timeCreated, timeSent, timeRecieved, OrderStatus.CREATED, null, 23,
						"kes"),
				new Order("2", null, dateCreated, timeCreated, timeSent, timeRecieved, OrderStatus.CREATED, null, 23,
						"kes"),
				new Order("3", null, dateCreated, timeCreated, timeSent, timeRecieved, OrderStatus.CREATED, null, 23,
						"kes"),
				new Order("4", null, dateCreated, timeCreated, timeSent, timeRecieved, OrderStatus.CREATED, null, 23,
						"kes"));

		for (Order o : listaOrders) {

			view.getOrderedProductsContainer().add(view.createOrdersContainer(o));

		}

		view.getOrderedProductsContainer().setAlignItems(Alignment.CENTER);
		view.getOrderedProductsContainer().setJustifyContentMode(JustifyContentMode.CENTER);

	}

	public void OrderDetailsScreen(String id) {
		UI.getCurrent().navigate("Order");
	}

}
