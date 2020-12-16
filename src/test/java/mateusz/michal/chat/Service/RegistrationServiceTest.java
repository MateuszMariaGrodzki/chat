package mateusz.michal.chat.Service;

import mateusz.michal.chat.Model.User;
import mateusz.michal.chat.Model.UserDTO;
import mateusz.michal.chat.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
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
        UserDTO userDTO = new UserDTO();
        userDTO.setName("");
        userDTO.setPassword("AlamaKota3@");
        userDTO.setEmail("Ala325@gmail.com");
        assertEquals("name_missing",registrationService.saveUserToDatabase(userDTO));
    }

    @Test
    @DisplayName("Test saveUserToDatabase with missing email")
    void shouldEmailBeMissing() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("AlamaKota");
        userDTO.setEmail("");
        userDTO.setPassword("AlamaKota3@");
        assertEquals("email_missing", registrationService.saveUserToDatabase(userDTO));
    }

    @Test
    @DisplayName("Test saveUserToDatabase with missing password")
    void shouldPasswordBeMissing(){
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Mateusz");
        userDTO.setEmail("mateusz@gmail.com");
        userDTO.setPassword("");
        assertEquals("password_missing", registrationService.saveUserToDatabase(userDTO));
    }

    @Test
    @DisplayName("Test for saveUserToDatabase with name occupied")
    void shouldNameBeInDatabase(){
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Mateusz");
        userDTO.setEmail("mateusz@gmail.com");
        userDTO.setPassword("AlaMaKota3@");
        User user = User.builder().name("Mateusz").email("mateusz@gmail.com").password("aaa").build();
        when(userRepository.findByName(anyString())).thenReturn(user);
        assertEquals("name_occupied", registrationService.saveUserToDatabase(userDTO));
    }

    @Test
    @DisplayName("Test for saveUserToDatabase with email occupied")
    void shouldEmailBeInDatabase(){
        UserDTO userDTO = new UserDTO();
        userDTO.setName("User1");
        userDTO.setEmail("mateusz@gmail.com");
        userDTO.setPassword("AlaMaKota");
        User user = User.builder().name("User2").email("mateusz@gmail.com").password("aaa").build();
        when(userRepository.findByEmail(anyString())).thenReturn(user);
        assertEquals("email_occupied",registrationService.saveUserToDatabase(userDTO));
    }

    @Test
    @DisplayName("Test for saveUserToDatabase with incorrect email")
    void shouldEmailBeIncorrect(){
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Mateusz");
        userDTO.setEmail("mateusz.gmail.com");
        userDTO.setPassword("Alokamak");
        assertEquals("email_incorrect", registrationService.saveUserToDatabase(userDTO));
    }

    @Test
    @DisplayName("Test for saveUserToDatabase with weak password")
    void  shouldPasswortBeWeak(){
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Mateusz");
        userDTO.setEmail("mateusz@gmail.com");
        userDTO.setPassword("AlaMaKota3");
        assertEquals("weak_password",registrationService.saveUserToDatabase(userDTO));
        userDTO.setPassword("Al3@");
        assertEquals("weak_password",registrationService.saveUserToDatabase(userDTO));
        userDTO.setPassword("AlaMaKota1234!@#$KotaMaAla");
        assertEquals("weak_password", registrationService.saveUserToDatabase(userDTO));
        userDTO.setPassword("AlaMaKota!@#$");
        assertEquals("weak_password", registrationService.saveUserToDatabase(userDTO));
        userDTO.setPassword("ALAMAKOTA123@");
        assertEquals("weak_password", registrationService.saveUserToDatabase(userDTO));
        userDTO.setPassword("alamakota123@");
        assertEquals("weak_password", registrationService.saveUserToDatabase(userDTO));
    }
}
