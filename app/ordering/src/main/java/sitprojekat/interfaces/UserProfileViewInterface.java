package sitprojekat.interfaces;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
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
	public VerticalLayout getUserProfileContainer();
	void setUserProfileContainer(VerticalLayout userProfileContainer);
	public H2 getTitleH2();
	public void setTitleH2(H2 titleH2);

}
