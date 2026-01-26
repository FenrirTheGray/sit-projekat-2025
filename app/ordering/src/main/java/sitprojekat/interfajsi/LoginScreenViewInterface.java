package sitprojekat.interfajsi;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;

public interface LoginScreenViewInterface {

	public EmailField getEmailField();
	public PasswordField getPasswordPasswordField();
	public Button getLoginButton();


}
