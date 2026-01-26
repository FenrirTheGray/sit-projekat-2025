package sitprojekat.interfajsi;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;

public interface AccountCreationViewInterface {

	public EmailField getEmailField();
	public PasswordField getPasswordPasswordField();
	public PasswordField getConfirmPasswordPasswordField();
	public Button getCreateAccountButton();

}
