package sitprojekat.interfaces;

import com.vaadin.flow.component.textfield.EmailField;

public interface ForgotenPasswordViewInterface {

	public EmailField getEmailField();
	public void showMessage(String message, boolean isError);
	public void clearForm();

}
