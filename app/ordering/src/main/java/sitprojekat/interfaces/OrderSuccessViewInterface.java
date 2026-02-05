package sitprojekat.interfaces;

import java.util.List;

import com.vaadin.flow.component.html.Span;

import sitprojekat.model.ProductInCart;

public interface OrderSuccessViewInterface {

	public Span getOrderIDSpan();
	public void setOrderIDSpan(String orderID);
	public Span getItemsOrderedSpan();
	public void setItemsOrderedSpan(List<ProductInCart> itemsOrdered);
}
