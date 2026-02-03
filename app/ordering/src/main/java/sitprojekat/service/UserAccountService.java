package sitprojekat.service;

import org.springframework.stereotype.Service;

import sitprojekat.model.UserAccount;

@Service
public class UserAccountService {

	private UserAccount user=new UserAccount("", "", "", "", "");
	
	public UserAccountService() {

	}


	

	
	public void setUser(UserAccount user) {
		this.user = user;
	}

	public UserAccount getUser() {
        return user;
    }
	

	public void setEmail(String email) {
		this.user.setEmail(email);
	}

	public void setAdress(String adress) {
		this.user.setAdress(adress);
	}


	public void setPhone(String phone) {
		this.user.setPhone(phone); 
	}

	public void setPassword(String password) {
		this.user.setPassword(password); 
	}
	
	
	
}



