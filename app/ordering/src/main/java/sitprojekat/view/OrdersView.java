package sitprojekat.view;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import java.util.List;

@StyleSheet("/css/style.css")
@Route(value = "Orders", layout = HeaderAndNavBar.class)
public class OrdersView extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	// Simple order record for display
	public record OrderItem(String id, String date, String items, double total, String status) {}

	private Grid<OrderItem> ordersGrid;
	private List<OrderItem> allOrders;

	public OrdersView() {
		setPadding(true);
		setSpacing(true);
		setSizeFull();

		H2 title = new H2("Pregled Porudžbina");
		title.addClassName("page-title");

		TextField filterTextBox = new TextField();
		filterTextBox.setPlaceholder("Pretraga porudžbina...");
		filterTextBox.setClearButtonVisible(true);
		filterTextBox.setPrefixComponent(VaadinIcon.SEARCH.create());
		filterTextBox.setWidth("350px");
		filterTextBox.addClassName("search-filter");
		filterTextBox.setValueChangeMode(ValueChangeMode.LAZY);
		filterTextBox.addValueChangeListener(e -> filterOrders(e.getValue()));

		HorizontalLayout filterContainer = new HorizontalLayout(filterTextBox);
		filterContainer.setWidthFull();
		filterContainer.setJustifyContentMode(JustifyContentMode.END);
		filterContainer.getStyle().set("padding", "16px 0");

		ordersGrid = new Grid<>(OrderItem.class, false);
		ordersGrid.addClassName("article-grid");
		ordersGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

		ordersGrid.addColumn(OrderItem::id)
			.setHeader("ID Porudžbine")
			.setSortable(true)
			.setWidth("120px")
			.setFlexGrow(0);

		ordersGrid.addColumn(OrderItem::date)
			.setHeader("Datum")
			.setSortable(true)
			.setWidth("150px")
			.setFlexGrow(0);

		ordersGrid.addColumn(OrderItem::items)
			.setHeader("Stavke")
			.setSortable(true)
			.setAutoWidth(true);

		ordersGrid.addColumn(order -> String.format("%.2f RSD", order.total()))
			.setHeader("Ukupno")
			.setSortable(true)
			.setWidth("130px")
			.setFlexGrow(0);

		ordersGrid.addColumn(new ComponentRenderer<>(order -> {
			Span badge = new Span(order.status());
			badge.addClassName("status-badge");
			badge.addClassName(order.status().equals("Završeno") ? "status-active" : "status-inactive");
			return badge;
		}))
			.setHeader("Status")
			.setWidth("120px")
			.setFlexGrow(0);

		// Sample data
		allOrders = List.of(
			new OrderItem("P001", "29.12.2025", "Article 1, Article 2", 150.00, "Završeno"),
			new OrderItem("P002", "28.12.2025", "Article 3", 80.00, "U obradi"),
			new OrderItem("P003", "27.12.2025", "Article 1, Article 4, Article 5", 320.00, "Završeno")
		);
		ordersGrid.setItems(allOrders);

		add(title, filterContainer, ordersGrid);
	}

	private void filterOrders(String filterText) {
		if (filterText == null || filterText.isEmpty()) {
			ordersGrid.setItems(allOrders);
		} else {
			String lowerFilter = filterText.toLowerCase();
			ordersGrid.setItems(allOrders.stream()
				.filter(order ->
					order.id().toLowerCase().contains(lowerFilter) ||
					order.items().toLowerCase().contains(lowerFilter) ||
					order.status().toLowerCase().contains(lowerFilter))
				.toList());
		}
	}
}
