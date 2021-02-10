package mateusz.michal.chat.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mateusz.michal.chat.Authorization.Model.LogoutErrorCode;
import mateusz.michal.chat.Authorization.Service.CookieService;
import mateusz.michal.chat.Structure.RespondStructure.JsonResponseFactory;
import mateusz.michal.chat.Structure.RespondStructure.MyError;
import mateusz.michal.chat.Structure.RespondStructure.ResponseEnum;
import mateusz.michal.chat.Structure.RespondStructure.SimpleDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@Component
public class MyLogoutHandler implements LogoutSuccessHandler {

    @Autowired
    CookieService cookieService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) throws IOException, ServletException {
        Cookie[] cookies = request.getCookies();
        Cookie tokenCookie = cookieService.getTokenCookieFromCookies(cookies);
        if(cookies == null){
            MyError myError = new MyError(400, LogoutErrorCode.COOKIES_NULL,
                    "server does not receive cookies from client");
            String object = generateJsonObjectForResponse(myError, ResponseEnum.ERROR);
            response.setContentType("application/json");
            response.getWriter().print(object);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else if(tokenCookie == null) {
            MyError myError = new MyError(400,LogoutErrorCode.TOKEN_MISSING,
                    "token cooke is not present in request cookies");
            String object = generateJsonObjectForResponse(myError,ResponseEnum.ERROR);
            response.setContentType("application/json");
            response.getWriter().print(object);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else{
            Cookie[] deletedCookies = cookieService.deleteAllCookiesFromBrowser(request.getCookies());
            for(Cookie cookie: deletedCookies){
                response.addCookie(cookie);
            }
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            String object = generateJsonObjectForResponse(null,ResponseEnum.DATA);
            PrintWriter out = response.getWriter();
            out.print(object);
        }
    }

    private String generateJsonObjectForResponse(MyError myError,ResponseEnum responseEnum){
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        Gson gson = builder.setPrettyPrinting().create();
        switch (responseEnum){
            case ERROR:{
                return gson.toJson(JsonResponseFactory.createResponse(ResponseEnum.ERROR,
                        Arrays.asList(myError),null,null));
            }
            case DATA:{
                return gson.toJson(JsonResponseFactory.createResponse(ResponseEnum.DATA,null,
                        new SimpleDataResponse("user has been succesfully logout"),null));
            }
        }
        throw new UnsupportedOperationException();
    }
}
