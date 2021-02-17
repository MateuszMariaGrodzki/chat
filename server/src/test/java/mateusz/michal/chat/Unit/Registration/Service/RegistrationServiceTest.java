package mateusz.michal.chat.Unit.Registration.Service;

import mateusz.michal.chat.Registration.Model.RegisterFormErrorCode;
import mateusz.michal.chat.Registration.Model.UserDTO;
import mateusz.michal.chat.Registration.Service.RegistrationService;
import mateusz.michal.chat.Structure.RespondStructure.MyError;
import mateusz.michal.chat.User.Model.User;
import mateusz.michal.chat.User.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
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
    public void nameNullTest(){
        //given
        UserDTO userDTO = UserDTO.builder().name(null).email("user1@gmail.com").password("AlamaKota123#").build();
        List<MyError> expected = Arrays.asList(MyError.builder().status(400).code(RegisterFormErrorCode.NAME_NULL)
                .title("parameter name is null").build());

        //when
        List<MyError> actual = registrationService.validateRequest(userDTO);

        //then
        assertIterableEquals(expected,actual);
    }

    @Test
    @DisplayName("Name empty test")
    public void nameEmptyTest(){
        //given
        UserDTO userDTO = UserDTO.builder().name("").email("user1@gmail.com").password("AlamaKota123#").build();
        List<MyError> expected = Arrays.asList(MyError.builder().status(422).code(RegisterFormErrorCode.NAME_MISSING)
                .title("parameter name isn't present").build());

        //when
        List<MyError> actual = registrationService.validateRequest(userDTO);

        //then
        assertIterableEquals(expected,actual);
    }

    @Test
    @DisplayName("Name occupied test")
    public void nameOccupiedTest(){
        //given
        UserDTO userDTO = UserDTO.builder().name("Maciek123").email("user1@gmail.com").password("AlamaKota123#").build();
        List<MyError> expected = Arrays.asList(MyError.builder().status(422).code(RegisterFormErrorCode.NAME_OCCUPIED)
                .title("In database exist user with that name").build());

        //when
        when(userRepository.findByName(anyString())).thenReturn(new User());
        List<MyError> actual = registrationService.validateRequest(userDTO);

        //then
        assertIterableEquals(expected,actual);
    }

    @Test
    @DisplayName("Name incorret test")
    public void nameIncorrectTest(){
        UserDTO userDTO = UserDTO.builder().name("Maciek123^").email("user1@gmail.com").password("AlamaKota123#").build();
        List<MyError> expected = Arrays.asList(MyError.builder().status(422).code(RegisterFormErrorCode.NAME_INCORRECT)
        .title("name can only have letters and digits").build());

        //when
        List<MyError> actual = registrationService.validateRequest(userDTO);

        //then
        assertIterableEquals(expected,actual);
    }







    //tests with wrong email
    //tests with wrong password


}
