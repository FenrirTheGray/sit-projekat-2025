package rs.ac.singidunum.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.cms.dto.LoginRequestDTO;
import rs.ac.singidunum.cms.dto.LoginResponseDTO;

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
        LoginResponseDTO responseDTO;

        try {
            responseDTO = httpService.post(httpService.AUTH_BASE_URL + "/login", loginRequest, LoginResponseDTO.class, false);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        if(responseDTO == null || responseDTO.token() == null || responseDTO.token().isBlank()) {
            return false;
        }

        userStoreService.setToken(responseDTO.token());

        return true;
    }

    public void logout(){
        userStoreService.deleteToken();
    }
}
