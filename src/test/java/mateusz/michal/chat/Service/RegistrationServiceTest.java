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
    @DisplayName("Test saveUserToDatabase with invalid name")
    void shouldNameBeMissing() throws Exception{
        User user = new User();
        user.setName("");
        user.setPassword("AlamaKota3@");
        user.setEmail("Ala325@gmail.com");
        assertEquals("name_missing",registrationService.saveUserToDatabase(user));
    }
}
