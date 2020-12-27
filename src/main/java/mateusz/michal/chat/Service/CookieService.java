package mateusz.michal.chat.Service;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
public class CookieService {

    public Cookie getTokenCookieFromCookies(Cookie[] cookies){
        if (cookies == null){
            return null;
        }
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("token")){
                return cookie;
            }
        }
        return null;
    }
}
