package sitprojekat.model;

public class UserAccount extends AbstractEntity{

	private String email;
	private String adress="";
	private String phone;
	private String password;
	
	public UserAccount(String id,String email, String adress, String phone, String password) {
		super(id);
		this.email = email;
		this.adress = adress;
		this.phone = phone;
		this.password = password;
	}

	public UserAccount() {
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
