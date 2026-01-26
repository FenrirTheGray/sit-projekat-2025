package sitprojekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        LoginRequestDTO loginRequest = new LoginRequestDTO(email, password);
        LoginResponseDTO responseDTO = httpService.post(httpService.AUTH_BASE_URL + "/login", loginRequest, LoginResponseDTO.class, false);

        if(responseDTO.token() == null || responseDTO.token().isEmpty()) return false;

        userStoreService.setToken(responseDTO.token());

        return true;
    }

    public void logout(){
        userStoreService.deleteToken();
    }


}
