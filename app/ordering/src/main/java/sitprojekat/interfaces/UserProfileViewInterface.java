package sitprojekat.interfaces;

import com.vaadin.flow.component.textfield.PasswordField;

public interface UserProfileViewInterface {

	public String getEmailField();
	public void setEmailField(String email);
	public String getAddressTextField();
	public void setAddressTextField(String address);
	public String getTelephoneTextField();
	public void setTelephoneTextField(String telephone);
	public String getPasswordPasswordField();
	public void setPasswordPasswordField(String password);
	public String getConfirmPasswordPasswordField();
	public void setConfirmPasswordPasswordField(PasswordField confirmPassword);

}
