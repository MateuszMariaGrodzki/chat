package mateusz.michal.chat.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.Cookie;

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
}
