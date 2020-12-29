package mateusz.michal.chat.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.Cookie;
import java.util.Arrays;

public class CookieServiceTest {

    @InjectMocks
    CookieService cookieService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test when cookie array is null")
    public void sholudReturnNullWhenThereIsNoCookies(){
        //given
        Cookie[] cookies = null;

        //when
        Cookie cookie = cookieService.getTokenCookieFromCookies(cookies);

        //then
        Assertions.assertNull(cookie);
    }

    @Test
    @DisplayName("Test getTokenCookieFromCookies when cookie array has 0 elements")
    public void shouldReturnNullWhenArrayHasZeroElements(){
        // given
        Cookie[] cookies = new Cookie[0];

        //when
        Cookie cookie = cookieService.getTokenCookieFromCookies(cookies);

        //then
        Assertions.assertNull(cookie);
    }

    @Test
    @DisplayName("Test getTokenCookieFromCookies when cookie array has cookies but token cookie is not present")
    public void shouldReturnNullWhenArrayDontHaveCookieWithToken(){
        // given
        Cookie cookie = new Cookie("cookie1", "value1");
        Cookie cookie2 = new Cookie("cookie2", "value2");
        Cookie cookie3 = new Cookie("cookie3", "value3");
        Cookie[] cookies = new Cookie[3];
        cookies[0] = cookie;
        cookies[1] = cookie2;
        cookies[2] = cookie3;

        //when
        Cookie returnCookie = cookieService.getTokenCookieFromCookies(cookies);

        //then
        Assertions.assertNull(returnCookie);
    }

    @Test
    @DisplayName("Test getTokenCookieFromCookies when token cookie is Present among cookies")
    public void shouldReturnTokenCookie(){
        // given
        Cookie cookie = new Cookie("cookie" , "value");
        Cookie tokenCookie = new Cookie("token", "realToken");
        Cookie[] cookies = new Cookie[2];
        cookies[0] = cookie;
        cookies[1] = tokenCookie;

        //when
        Cookie returnCookie = cookieService.getTokenCookieFromCookies(cookies);

        //then
        Assertions.assertEquals("realToken",returnCookie.getValue());
    }

    @Test
    @DisplayName("Test generateRefreshCookie has good cookie name")
    public void shouldCookieNameByToken(){
        // given
        String token = "aaa";

        //when
        Cookie cookie = cookieService.generateRefreshCookie(token);

        //then
        Assertions.assertEquals("token",cookie.getName());
    }

    @Test
    @DisplayName("Test is generateRefreshCookie create httpOnly Cookie")
    public void shouldCokkieByHttpOnly(){
        //given
        String token = "aaa";

        //when
        Cookie cookie = cookieService.generateRefreshCookie(token);

        //then
        Assertions.assertTrue(cookie.isHttpOnly());
    }

    @Test
    @DisplayName("Test is generateRefreshCookie create not secure Cookie")
    public void shouldCookieByNotSecured(){
        //given
        String token = "aaa";

        //when
        Cookie cookie = cookieService.generateRefreshCookie(token);

        //then
        Assertions.assertFalse(cookie.getSecure());
    }
}
