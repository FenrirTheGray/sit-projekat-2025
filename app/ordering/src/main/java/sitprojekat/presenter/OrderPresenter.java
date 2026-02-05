package sitprojekat.presenter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.spring.annotation.UIScope;

import sitprojekat.model.Article;
import sitprojekat.model.Combo;
import sitprojekat.model.Order;
import sitprojekat.model.OrderedProduct;
import sitprojekat.service.OrderService;
import sitprojekat.view.OrderView;

@Component
@UIScope
public class OrderPresenter {

	private OrderView view;
	private OrderService service;
	private String orderID;

	public OrderPresenter(OrderService service) {
		this.service = service;
	}

	public void setView(OrderView view) {
		this.view = view;
		view.SetOrderID(orderID);
	}

	public void backClick() {
		UI.getCurrent().getPage().getHistory().back();
	}

	public void findByID(String orderID) {
		Order order=service.findByID(orderID);

		String[] getCreatedAtDateTime=order.getCreatedAt().split(",");
		String CreatedAtDate=getCreatedAtDateTime[0].trim();
		String CreatedAtTime=getCreatedAtDateTime[1].trim();
		
		view.setTitleSpan("Porudzbina " + order.getId());
		view.setOrderDateSpan("Datum Porudzbine: " + CreatedAtDate);
		view.setOrderTimeSpan("Vreme Porudzbine: " + CreatedAtTime);
		if(order.getSentAtTime()!=null) {
			view.setOrderTimeSentSpan("Porudzbina poslata: " + order.getSentAtTime());
			}
			else {
				view.setOrderTimeSentSpan("Nije jos poslata");
			}
			if(order.getSentAtTime()!=null) {
				view.setOrderTimeDeliveredSpan("Vreme isporuke: " + order.getRecievedtedAtTime());
			}
			else {
					view.setOrderTimeSentSpan("Nije jos uzeta");
			}
		
		double totalSumDouble = 0;
		for (int i = 0; i < order.getOrderedProducts().size(); i++) {
			OrderedProduct p = order.getOrderedProducts().get(i);
			Span OrderedProductSpan = new Span(p.getOrderedProduct().getName() + " kolicina " + p.getAmount());
			OrderedProductSpan.addClassName("whiteText");
			view.getOrderedProductsContainer().add(OrderedProductSpan);
			totalSumDouble += p.getOrderedProduct().getBasePrice();
		}

		view.setOrderStatusSpan("Status: " + order.getStatus());
		view.setOrderTotalSumSpan("Cena: " + totalSumDouble);
		view.setOrderPaymentType("Nacin placanja: " + order.getPaymentType());

	}


	public void findByID1(String string) {

		List<Article> listaArtikala = List.of(
				new Article("1", "Artical1", "modifikator1", "slika1", 250.0, true, null, null),
				new Article("2", "Artical2", "modifikator2", "slika1", 350.0, true, null, null),
				new Article("3", "Artical3", "modifikator3", "slika1", 650.0, true, null, null)); 

		Combo combo = new Combo("id1", "n1", "d1", "", 23, false, null, listaArtikala, listaArtikala, listaArtikala);

		List<OrderedProduct> orderedProduct = List.of(new OrderedProduct("id1", 2, combo), new OrderedProduct("id2", 3,
				new Article("1", "Artical1", "modifikator1", "slika1", 250.0, true, null, null)));

		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd MM yyyy");
		LocalDate dateCreated = LocalDate.parse("2025-12-21");
		String formattedDdateCreated = dateCreated.format(formatterDate); // lespi format za datum

		LocalTime timeCreated = LocalTime.parse("16:30");
		LocalTime timeSent = LocalTime.parse("16:35");
		LocalTime timeRecieved = LocalTime.parse("17:30");

		Order order = new Order();


		String[] getCreatedAtDateTime=order.getCreatedAt().split(",");
		String CreatedAtDate=getCreatedAtDateTime[0].trim();
		String CreatedAtTime=getCreatedAtDateTime[1].trim();
		
		view.setTitleSpan("Porudzbina " + order.getId());
		view.setOrderDateSpan("Datum Porudzbine: " + formattedDdateCreated);
		view.setOrderTimeSpan("Vreme Porudzbine: " + CreatedAtTime);
		if(order.getSentAtTime()!=null) {
		view.setOrderTimeSentSpan("Porudzbina poslata: " + order.getSentAtTime());
		}
		else {
			view.setOrderTimeSentSpan("Nije jos poslata");
		}
		if(order.getSentAtTime()!=null) {
			view.setOrderTimeDeliveredSpan("Vreme isporuke: " + order.getRecievedtedAtTime());
		}
		else {
				view.setOrderTimeSentSpan("Nije jos uzeta");
		}
		

		double totalSumDouble = 0;
		for (int i = 0; i < order.getOrderedProducts().size(); i++) {
			OrderedProduct p = order.getOrderedProducts().get(i);
			Span OrderedProductSpan = new Span(p.getOrderedProduct().getName() + " " + p.getAmount());
			OrderedProductSpan.addClassName("whiteText");
			view.getOrderedProductsContainer().add(OrderedProductSpan);
			totalSumDouble += p.getOrderedProduct().getBasePrice();
		}

		view.setOrderStatusSpan("Status: " + order.getStatus());
		view.setOrderTotalSumSpan("Cena: " + totalSumDouble);
		view.setOrderPaymentType("Nacin placanja: " + order.getPaymentType());

	}

	public void setOrder(String id) {
		this.orderID=id;
		
	}



}
