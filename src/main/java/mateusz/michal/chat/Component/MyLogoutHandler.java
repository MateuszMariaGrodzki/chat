package mateusz.michal.chat.Component;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class MyLogoutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            try {
                response.sendError(403);
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
