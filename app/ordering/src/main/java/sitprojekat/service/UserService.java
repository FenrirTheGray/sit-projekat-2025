package sitprojekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

import sitprojekat.dto.LoginRequestDTO;
import sitprojekat.dto.LoginResponseDTO;

@Service
public class UserService {

    @Autowired
    UserStoreService userStoreService;
    @Autowired
    HttpService httpService;


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

        return true;
    }catch (Exception e) {
		return false;
	}
    }

    public void logout(){
        userStoreService.deleteToken();
    }


}
