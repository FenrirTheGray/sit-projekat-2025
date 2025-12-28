package sitprojekat.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value = "Orders", layout = HeaderAndNavBar.class)
public class OrdersView extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public OrdersView() {
		getStyle().set("background-color", "#204824");
		setPadding(true);
		setSpacing(true);
		setSizeFull();

		H2 title = new H2("Pregled Porudžbina");
		title.getStyle().set("color", "#ffffff");

		TextField filterTextBox = new TextField();
		filterTextBox.setPlaceholder("Pretraga porudžbina...");
		filterTextBox.setClearButtonVisible(true);
		filterTextBox.setSuffixComponent(VaadinIcon.SEARCH.create());
		filterTextBox.getStyle().set("background-color", "#ffffff");
		filterTextBox.getStyle().set("border-radius", "30px");
		filterTextBox.setWidth("350px");

		HorizontalLayout filterContainer = new HorizontalLayout(filterTextBox);
		filterContainer.setWidthFull();
		filterContainer.setJustifyContentMode(JustifyContentMode.END);

		// Placeholder grid for orders - will be populated with actual order data later
		Grid<String> ordersGrid = new Grid<>();
		ordersGrid.addColumn(item -> item).setHeader("Order ID");
		ordersGrid.getStyle().set("background", "transparent");

		add(title, filterContainer, ordersGrid);
	}
}
