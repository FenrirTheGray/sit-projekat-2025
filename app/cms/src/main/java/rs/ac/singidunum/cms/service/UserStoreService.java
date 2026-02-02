package rs.ac.singidunum.cms.service;

import com.vaadin.flow.server.VaadinService;
import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UserStoreService {

    public String getFromCookie(String cookieName){
        var request = VaadinService.getCurrentRequest();
        if (request == null) {
            return "";
        }

        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return "";
        }

        for (Cookie cookie : cookies) {
            if (cookieName.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return "";
    }

    public void setToCookie(String cookieName, String cookieValue){
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setPath("/");

        System.out.println(cookieName + ": " + cookieValue);
        VaadinService.getCurrentResponse().addCookie(cookie);
    }

    public void deleteCookie(String cookieName){
        Cookie cookie = new Cookie(cookieName, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);

        VaadinService.getCurrentResponse().addCookie(cookie);
    }

    public String getToken() throws ExecutionException, InterruptedException {
        return getFromCookie("accessToken");
    }
    public void setToken(String token){
        setToCookie("accessToken", token);
    }
    public void deleteToken(){
        deleteCookie("accessToken");
    }

    public String getEmail() throws ExecutionException, InterruptedException{
        return getFromCookie("email");
    }
    public void setEmail(String email){
        setToCookie("email", email);
    }

}
