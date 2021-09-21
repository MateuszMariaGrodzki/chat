package mateusz.michal.chat.Authorization.Service;

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

    public Cookie generateRefreshCookie(String token){
        Cookie cookie = new Cookie("token",token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        return cookie;
    }

    public Cookie[] deleteAllCookiesFromBrowser(Cookie[] cookies){
        Cookie[] deletedCookies = new Cookie[cookies.length];
        for(int i = 0 ; i < cookies.length ; ++i){
            String cookieName = cookies[i].getName();
            Cookie cookieToDelete = new Cookie(cookieName, null);
            cookieToDelete.setMaxAge(0);
            cookieToDelete.setHttpOnly(true);
            cookieToDelete.setSecure(false);
            cookieToDelete.setPath("/");
            deletedCookies[i] = cookieToDelete;
        }
        return deletedCookies;
    }

}
