package mateusz.michal.chat.Unit.Registration.Service;

import mateusz.michal.chat.Registration.Model.RegisterFormErrorCode;
import mateusz.michal.chat.Registration.Model.UserDTO;
import mateusz.michal.chat.Registration.Service.RegistrationService;
import mateusz.michal.chat.Structure.RespondStructure.IJsonResponse;
import mateusz.michal.chat.Structure.RespondStructure.MyError;
import mateusz.michal.chat.User.Model.User;
import mateusz.michal.chat.User.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class RegistrationServiceTest {

    @InjectMocks
    RegistrationService registrationService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    // tests with wrong name
    @Test
    @DisplayName("Name null test")
    public void NameNullTest(){
        //given
        UserDTO userDTO = UserDTO.builder().name(null).email("user1@gmail.com").password("AlamaKota123#").build();
        List<MyError> expected = Arrays.asList(MyError.builder().status(400).code(RegisterFormErrorCode.NAME_NULL)
                .title("parameter name is null").build());

        //when
        List<MyError> actual = registrationService.validateRequest(userDTO);
        when(userRepository.findByEmail(anyString())).thenReturn(User.builder().email("aaa@gmail.com").build());

        //then
        assertIterableEquals(expected,actual);
    }

    //tests with wrong email

    //tests with wrong password


}
