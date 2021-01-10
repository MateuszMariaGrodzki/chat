package mateusz.michal.chat.Component;

import mateusz.michal.chat.Service.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyLogoutHandler implements LogoutHandler {

    @Autowired
    CookieService cookieService;

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        Cookie[] cookies = request.getCookies();
        Cookie tokenCookie = cookieService.getTokenCookieFromCookies(cookies);
        if(cookies == null || tokenCookie == null){
            try {
                response.sendError(401);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            for (Cookie cookie : request.getCookies()) {
                String cookieName = cookie.getName();
                Cookie cookieToDelete = new Cookie(cookieName, null);
                cookieToDelete.setMaxAge(0);
                cookieToDelete.setHttpOnly(true);
                cookieToDelete.setSecure(false);
                cookieToDelete.setPath("/");
                response.addCookie(cookieToDelete);
            }
        }

    }
}
