package sitprojekat.view;

import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import sitprojekat.interfaces.OrderViewInterface;
import sitprojekat.model.Article;
import sitprojekat.presenter.OrderPresenter;

@CssImport("./style/style.css")
@Route(value = "Order", layout = HeaderAndNavBar.class)
public class OrderView extends VerticalLayout implements OrderViewInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -354889220122711558L;

	private Icon backArrowIcon = VaadinIcon.ARROW_BACKWARD.create();
	private Button backButton = new Button("Povratak", backArrowIcon);
	private Span titleSpan = new Span();
	private Span orderDateSpan = new Span();
	private Span orderTimeSpan = new Span();
	private Span orderTimeSentSpan = new Span();
	private Span orderTimeDeliveredSpan = new Span();
	private Span orderStatusSpan = new Span();
	private Span orderTotalSumSpan = new Span();
	private Span orderPaymentType = new Span();
	private VerticalLayout mainContainer = new VerticalLayout();
	private VerticalLayout orderInformationContainer = new VerticalLayout();
	private VerticalLayout orderedProductsContainer = new VerticalLayout();
	private final OrderPresenter presenter;

	public OrderView(OrderPresenter presenter) {
		this.presenter = presenter;
		presenter.setView(this);
		presenter.findByID1("1");

		backButton.addClassName("brownButton");
		backButton.addClickListener(e -> presenter.backClick());

		orderInformationContainer.addClassName("orderContainer");
		orderInformationContainer.setSpacing(false);

		titleSpan.addClassName("boldText2");
		orderDateSpan.addClassName("whiteText");
		orderTimeSpan.addClassName("whiteText3");
		orderTimeSentSpan.addClassName("whiteText");
		orderTimeDeliveredSpan.addClassName("whiteText3");

		orderStatusSpan.addClassName("whiteText3");
		orderTotalSumSpan.addClassName("whiteText");
		orderPaymentType.addClassName("whiteText4");

		orderedProductsContainer.addClassName("orderedProductsContainer");
		orderedProductsContainer.setSpacing(false);

		orderInformationContainer.add(titleSpan, orderDateSpan, orderTimeSpan, orderTimeSentSpan,
				orderTimeDeliveredSpan, orderedProductsContainer, orderStatusSpan, orderTotalSumSpan, orderPaymentType);
		mainContainer.add(orderInformationContainer);
		mainContainer.setAlignItems(Alignment.CENTER);
		mainContainer.setJustifyContentMode(JustifyContentMode.CENTER);

		add(backButton, mainContainer);
	}

	@Override
	public void setTitleSpan(String title) {
		this.titleSpan.setText(title);

	}

	@Override
	public void setOrderDateSpan(String orderDate) {
		this.orderDateSpan.setText(orderDate);

	}

	@Override
	public void setOrderTimeSpan(String orderTime) {
		this.orderTimeSpan.setText(orderTime);

	}

	@Override
	public void setOrderTimeSentSpan(String timeSpent) {
		this.orderTimeSentSpan.setText(timeSpent);

	}

	@Override
	public void setOrderTimeDeliveredSpan(String orderTimeDelivered) {
		this.orderTimeDeliveredSpan.setText(orderTimeDelivered);

	}

	@Override
	public void setOrderStatusSpan(String orderStatus) {
		this.orderStatusSpan.setText(orderStatus);

	}

	@Override
	public void setOrderTotalSumSpan(String orderTotalSum) {
		this.orderTotalSumSpan.setText(orderTotalSum);

	}

	@Override
	public void setOrderPaymentType(String orderPaymentType) {
		this.orderPaymentType.setText(orderPaymentType);

	}

	@Override
	public VerticalLayout getOrderInformationContainer() {
		return orderInformationContainer;
	}

	@Override
	public void setOrderInformationContainer(VerticalLayout orderInformationContainer) {
		this.orderInformationContainer = orderInformationContainer;

	}

	@Override
	public VerticalLayout getOrderedProductsContainer() {
		return orderedProductsContainer;
	}

	@Override
	public void setOrderedProductsContainer(VerticalLayout orderedProducts) {
		this.orderedProductsContainer = orderedProducts;
	}

}
