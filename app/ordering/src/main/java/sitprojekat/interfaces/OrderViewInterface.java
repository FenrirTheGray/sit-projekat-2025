package sitprojekat.interfaces;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public interface OrderViewInterface {


	
	public void setTitleSpan(String title);
	public void setOrderDateSpan(String orderDate);
	public void setOrderTimeSpan(String orderTime);
	public void setOrderTimeSentSpan(String timeSpent);
	public void setOrderTimeDeliveredSpan(String orderTimeDelivered);
	public void setOrderStatusSpan(String orderStatus);
	public void setOrderTotalSumSpan(String orderTotalSum);
	public void setOrderPaymentType(String orderPaymentType);
	public VerticalLayout getOrderInformationContainer();
	public void setOrderInformationContainer(VerticalLayout orderInformationContainer);
	public VerticalLayout getOrderedProductsContainer();
	public void setOrderedProductsContainer(VerticalLayout orderedProducts);
	public void SetOrderID(String id);
}
