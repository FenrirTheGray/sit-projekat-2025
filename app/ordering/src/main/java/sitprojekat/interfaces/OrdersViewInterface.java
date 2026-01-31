package sitprojekat.interfaces;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import sitprojekat.model.Order;

public interface OrdersViewInterface {

	public VerticalLayout getOrderedProductsContainer();

	public void setOrderedProductsContainer(VerticalLayout orderedProductsContainer);
	
	public VerticalLayout createOrdersContainer(Order order);
}
