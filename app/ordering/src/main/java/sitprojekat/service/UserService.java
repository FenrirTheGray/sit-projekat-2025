package sitprojekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

import sitprojekat.dto.LoginRequestDTO;
import sitprojekat.dto.LoginResponseDTO;
import sitprojekat.dto.PasswordResetRequestDTO;
import sitprojekat.dto.RegisterRequestDTO;
import sitprojekat.dto.UserResponseDTO;

@Service
public class UserService {

    @Autowired
    UserStoreService userStoreService;
    @Autowired
    HttpService httpService;
    @Autowired 
    UserAccountService UserAccountService;
    
    public void register(String email, String password){
        throw new UnsupportedOperationException();
    }

    public boolean login(String email, String password){
    	
    	try {
        LoginRequestDTO loginRequest = new LoginRequestDTO(email, password);
        LoginResponseDTO responseDTO = httpService.post(httpService.AUTH_BASE_URL + "/login", loginRequest, LoginResponseDTO.class, false);

        if(responseDTO.token() == null || responseDTO.token().isEmpty()) {
        	
        	Notification notification = Notification.show("uneti podaci se ne podudaraju sa nalogom", 3000, Notification.Position.BOTTOM_CENTER); // sta pise , koliko traje, pozicija
    	    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);// koje je boje
        	
        	
        	return false;
        }
        userStoreService.setToken(responseDTO.token());
        System.out.println(email+"  "+password);
        UserAccountService.setEmail(email);
        System.out.println(UserAccountService.getUser().getEmail());
        return true;
    }catch (Exception e) {
		return false;
	}
    }
    public boolean createAccount(String email, String password){
    	
    	
    	try {
    		RegisterRequestDTO  registerRequestDTO = new RegisterRequestDTO (email, password);
    		UserResponseDTO accountCreated= httpService.post(httpService.AUTH_BASE_URL + "/register", registerRequestDTO, UserResponseDTO.class, false);
        
        if(accountCreated!=null) {
    		Notification notification = Notification.show("nalog je kreiran", 3000, Notification.Position.BOTTOM_CENTER); // sta pise , koliko traje, pozicija
    	    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);// koje je boje
    	    return true;
        	
    	}
    	
    	Notification notification = Notification.show("taj email je vec iskoriscen", 3000, Notification.Position.BOTTOM_CENTER); // sta pise , koliko traje, pozicija
	    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);// koje je boje
    	
		return false;

    }catch (Exception e) {
		return false;
	}
    }
    
    public boolean forgotPassword(String email) {
    	
    	PasswordResetRequestDTO  passwordResetRequestDTO = new PasswordResetRequestDTO (email);
    	UserResponseDTO passwordSent= httpService.post(httpService.AUTH_BASE_URL + "/password-reset/request", passwordResetRequestDTO, UserResponseDTO.class ,false);
    	
    	if(passwordSent!=null) {
    		Notification notification = Notification.show("podaci su poslati na email", 3000, Notification.Position.BOTTOM_CENTER); // sta pise , koliko traje, pozicija
    	    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);// koje je boje
        	
    	}
    	
    	Notification notification = Notification.show("taj email nije prijavljen", 3000, Notification.Position.BOTTOM_CENTER); // sta pise , koliko traje, pozicija
	    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);// koje je boje
    	
		return false;	
    }

    
    
    
    public void logout(){
        userStoreService.deleteToken();
    }


}
