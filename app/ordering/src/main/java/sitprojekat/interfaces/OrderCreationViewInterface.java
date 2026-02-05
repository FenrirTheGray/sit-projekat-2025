package sitprojekat.interfaces;

import com.vaadin.flow.component.textfield.TextField;

public interface OrderCreationViewInterface {
	public void setTotalPrice(double totalPrice);

	public TextField getAddressTextField();

	public void setAddressTextField(String address);

	public TextField getTelephoneTextField();

	public void setTelephoneTextField(String telephone);
}
