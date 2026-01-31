package sitprojekat.service;

import org.springframework.stereotype.Service;

import sitprojekat.model.UserAccount;

@Service
public class UserAccountService {

	private UserAccount user=new UserAccount("id","email", "adresa", "telefon", "pass");
	
	public UserAccountService() {

	}


	

	
	public UserAccount getUser() {
        return user;
    }
}
