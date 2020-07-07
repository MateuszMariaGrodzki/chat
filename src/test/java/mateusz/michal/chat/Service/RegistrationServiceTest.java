package mateusz.michal.chat.Service;

import mateusz.michal.chat.Model.User;
import mateusz.michal.chat.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


public class RegistrationServiceTest {

    @InjectMocks
    RegistrationService registrationService;

    @Mock
    UserRepository userRepository;

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

    @Test
    @DisplayName("Test for saveUserToDatabase with name occupied")
    void shouldNameBeInDatabase(){
        User user = new User();
        user.setName("Mateusz");
        user.setEmail("mateusz@gmail.com");
        user.setPassword("AlaMaKota3@");
        User userFromDatabase = new User();
        user.setName("Mateusz");
        user.setEmail("mati402@onet.pl");
        user.setPassword("ala");
        when(userRepository.findByName(anyString())).thenReturn(userFromDatabase);
        assertEquals("name_occupied", registrationService.saveUserToDatabase(user));
    }

    @Test
    @DisplayName("Test for saveUserToDatabase with email occupied")
    void shouldEmailBeInDatabase(){
        User user = new User();
        user.setName("User1");
        user.setEmail("mateusz@gmail.com");
        user.setPassword("AlaMaKota");
        User userFromDatabase = new User();
        user.setName("User2");
        user.setEmail("mateusz@gmail.com");
        user.setPassword("Kot231");
        when(userRepository.findByEmail(anyString())).thenReturn(userFromDatabase);
        assertEquals("email_occupied",registrationService.saveUserToDatabase(user));
    }
}
