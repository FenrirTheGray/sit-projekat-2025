package sitprojekat.interfaces;

import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

public interface UserProfileViewInterface {

	public String getEmailField();
	public void setEmailField(EmailField email);
	public String getAddressTextField();
	public void setAddressTextField(TextField address);
	public String getTelephoneTextField();
	public void setTelephoneTextField(TextField telephone);
	public String getPasswordPasswordField();
	public void setPasswordPasswordField(PasswordField password);
	public String getConfirmPasswordPasswordField();
	public void setConfirmPasswordPasswordField(PasswordField confirmPassword);

}
