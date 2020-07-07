package mateusz.michal.chat.Service;

import mateusz.michal.chat.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;


public class RegistrationServiceTest {

    @InjectMocks
    RegistrationService registrationService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test saveUserToDatabase with missing name")
    void shouldNameBeMissing() {
        User user = new User();
        user.setName("");
        user.setPassword("AlamaKota3@");
        user.setEmail("Ala325@gmail.com");
        assertEquals("name_missing",registrationService.saveUserToDatabase(user));
    }

    @Test
    @DisplayName("Test saveUserToDatabase with missing email")
    void shouldEmailBeMissing() {
        User user = new User();
        user.setName("AlamaKota");
        user.setEmail("");
        user.setPassword("AlamaKota3@");
        assertEquals("email_missing", registrationService.saveUserToDatabase(user));
    }

    @Test
    @DisplayName("Test saveUserToDatabase with missing password")
    void shouldPasswordBeMissing(){
        User user = new User();
        user.setName("Mateusz");
        user.setEmail("mateusz@gmail.com");
        user.setPassword("");
        assertEquals("password_missing", registrationService.saveUserToDatabase(user));
    }

}
