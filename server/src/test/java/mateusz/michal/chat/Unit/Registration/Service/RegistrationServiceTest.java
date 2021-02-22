package mateusz.michal.chat.Unit.Registration.Service;

import mateusz.michal.chat.Registration.Model.RegisterFormErrorCode;
import mateusz.michal.chat.Registration.Model.UserDTO;
import mateusz.michal.chat.Registration.Service.RegistrationService;
import mateusz.michal.chat.Structure.RespondStructure.MyError;
import mateusz.michal.chat.User.Model.User;
import mateusz.michal.chat.User.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
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

    @Nested
    class validateRequestMethodTests {

        @Nested
        class NamePropertyTests {

            @Test
            @DisplayName("Name null test")
            public void nameNullTest() {
                //given
                UserDTO userDTO = UserDTO.builder().name(null).email("user1@gmail.com").password("AlamaKota123#").build();
                List<MyError> expected = Arrays.asList(MyError.builder().status(400).code(RegisterFormErrorCode.NAME_NULL)
                        .title("parameter name is null").build());

                //when
                List<MyError> actual = registrationService.validateRequest(userDTO);

                //then
                assertIterableEquals(expected, actual);
            }

            @Test
            @DisplayName("Name empty test")
            public void nameEmptyTest() {
                //given
                UserDTO userDTO = UserDTO.builder().name("").email("user1@gmail.com").password("AlamaKota123#").build();
                List<MyError> expected = Arrays.asList(MyError.builder().status(422).code(RegisterFormErrorCode.NAME_MISSING)
                        .title("parameter name isn't present").build());

                //when
                List<MyError> actual = registrationService.validateRequest(userDTO);

                //then
                assertIterableEquals(expected, actual);
            }

            @Test
            @DisplayName("Name occupied test")
            public void nameOccupiedTest() {
                //given
                UserDTO userDTO = UserDTO.builder().name("Maciek123").email("user1@gmail.com").password("AlamaKota123#").build();
                List<MyError> expected = Arrays.asList(MyError.builder().status(422).code(RegisterFormErrorCode.NAME_OCCUPIED)
                        .title("In database exist user with that name").build());

                //when
                when(userRepository.findByName(anyString())).thenReturn(new User());
                List<MyError> actual = registrationService.validateRequest(userDTO);

                //then
                assertIterableEquals(expected, actual);
            }

            @Test
            @DisplayName("Name incorret test")
            public void nameIncorrectTest() {
                //given
                UserDTO userDTO = UserDTO.builder().name("Maciek123^").email("user1@gmail.com").password("AlamaKota123#").build();
                List<MyError> expected = Arrays.asList(MyError.builder().status(422).code(RegisterFormErrorCode.NAME_INCORRECT)
                        .title("name can only have letters and digits").build());

                //when
                List<MyError> actual = registrationService.validateRequest(userDTO);

                //then
                assertIterableEquals(expected, actual);
            }
        }


        @Nested
        class EmailPropertyTests {

            @Test
            @DisplayName("Email null test")
            public void emailNullTest() {
                //given
                UserDTO userDTO = UserDTO.builder().name("Maciek123").email(null).password("AlamaKota123#").build();
                List<MyError> expected = Arrays.asList(MyError.builder().status(400).code(RegisterFormErrorCode.EMAIL_NULL)
                        .title("parameter email is null").build());

                //when
                List<MyError> actual = registrationService.validateRequest(userDTO);

                //then
                assertIterableEquals(expected, actual);
            }

            @Test
            @DisplayName("Email empty test")
            public void emailEmptyTest() {
                //given
                UserDTO userDTO = UserDTO.builder().name("Maciek123").email("").password("AlamaKota123#").build();
                List<MyError> expected = Arrays.asList(MyError.builder().status(422).code(RegisterFormErrorCode.EMAIL_MISSING)
                        .title("parameter email isn't present").build());

                //when
                List<MyError> actual = registrationService.validateRequest(userDTO);

                //then
                assertIterableEquals(expected, actual);
            }

            @Test
            @DisplayName("Email occupied test")
            public void emailOccupiedTest() {
                //given
                UserDTO userDTO = UserDTO.builder().name("Maciek123").email("user123@gmail.com").password("AlamaKota123#").build();
                List<MyError> expected = Arrays.asList(MyError.builder().status(422).code(RegisterFormErrorCode.EMAIL_OCCUPIED)
                        .title("In database exist user with that email").build());

                //when
                when(userRepository.findByEmail(anyString())).thenReturn(new User());
                List<MyError> actual = registrationService.validateRequest(userDTO);

                //then
                assertIterableEquals(expected, actual);
            }

            @Test
            @DisplayName("Email incorrect test")
            public void emailIncorrectTest() {
                //given
                UserDTO userDTO = UserDTO.builder().name("Maciek123").email("aaa").password("AlamaKota123#").build();
                List<MyError> expected = Arrays.asList(MyError.builder().status(422).code(RegisterFormErrorCode.EMAIL_INCORRECT)
                        .title("email has bad format").build());

                //when
                List<MyError> actual = registrationService.validateRequest(userDTO);

                //then
                assertIterableEquals(expected, actual);
            }
        }

        @Nested
        class PasswordPropertyTests {

            @Test
            @DisplayName("Password null test")
            public void passwordNullTest() {
                //given
                UserDTO userDTO = UserDTO.builder().name("Maciek123").email("Maciek123@gmail.com").password(null).build();
                List<MyError> expected = Arrays.asList(MyError.builder().status(400).code(RegisterFormErrorCode.PASSWORD_NULL)
                        .title("parameter password is null").build());

                //when
                List<MyError> actual = registrationService.validateRequest(userDTO);

                //then
                assertIterableEquals(expected, actual);
            }

            @Test
            @DisplayName("Password empty test")
            public void passwordEmptyTest() {
                //given
                UserDTO userDTO = UserDTO.builder().name("Maciek123").email("Maciek123@gmail.com").password("").build();
                List<MyError> expected = Arrays.asList(MyError.builder().status(422).code(RegisterFormErrorCode.PASSWORD_MISSING)
                        .title("parameter password isn't present").build());

                //when
                List<MyError> actual = registrationService.validateRequest(userDTO);

                //then
                assertIterableEquals(expected, actual);
            }

            @Test
            @DisplayName("Password weak test with missing digit")
            public void passworWeakTestNoDigit() {
                //given
                UserDTO userDto = UserDTO.builder().name("Maciek123").email("Maciek123@gmail.com").password("Alama%Kota").build();
                List<MyError> expected = Arrays.asList(MyError.builder().status(422).code(RegisterFormErrorCode.WEAK_PASSWORD)
                        .title("password is too weak").build());

                //when
                List<MyError> actual = registrationService.validateRequest(userDto);

                //then
                assertIterableEquals(expected, actual);
            }

            @Test
            @DisplayName("Password weak test with missing special sign")
            public void passworWeakTestNoSign() {
                //given
                UserDTO userDto = UserDTO.builder().name("Maciek123").email("Maciek123@gmail.com").password("Alama3Kota").build();
                List<MyError> expected = Arrays.asList(MyError.builder().status(422).code(RegisterFormErrorCode.WEAK_PASSWORD)
                        .title("password is too weak").build());

                //when
                List<MyError> actual = registrationService.validateRequest(userDto);

                //then
                assertIterableEquals(expected, actual);
            }

            @Test
            @DisplayName("Password weak test with missing upper case letter")
            public void passworWeakTestNoUpperLetter() {
                //given
                UserDTO userDto = UserDTO.builder().name("Maciek123").email("Maciek123@gmail.com").password("alama3%kota").build();
                List<MyError> expected = Arrays.asList(MyError.builder().status(422).code(RegisterFormErrorCode.WEAK_PASSWORD)
                        .title("password is too weak").build());

                //when
                List<MyError> actual = registrationService.validateRequest(userDto);

                //then
                assertIterableEquals(expected, actual);
            }

            @Test
            @DisplayName("Password weak test with missing lower case letter")
            public void passworWeakTestNoLowerLetter() {
                //given
                UserDTO userDto = UserDTO.builder().name("Maciek123").email("Maciek123@gmail.com").password("ALAMA3%KOTA").build();
                List<MyError> expected = Arrays.asList(MyError.builder().status(422).code(RegisterFormErrorCode.WEAK_PASSWORD)
                        .title("password is too weak").build());

                //when
                List<MyError> actual = registrationService.validateRequest(userDto);

                //then
                assertIterableEquals(expected, actual);
            }

            @Test
            @DisplayName("Password weak test with missing digit and special sign")
            public void passworWeakTestNoDigitNoSign() {
                //given
                UserDTO userDto = UserDTO.builder().name("Maciek123").email("Maciek123@gmail.com").password("AlamaKota").build();
                List<MyError> expected = Arrays.asList(MyError.builder().status(422).code(RegisterFormErrorCode.WEAK_PASSWORD)
                        .title("password is too weak").build());

                //when
                List<MyError> actual = registrationService.validateRequest(userDto);

                //then
                assertIterableEquals(expected, actual);
            }

            @Test
            @DisplayName("Password short test")
            public void passworShortTest() {
                //given
                UserDTO userDto = UserDTO.builder().name("Maciek123").email("Maciek123@gmail.com").password("Al3$").build();
                List<MyError> expected = Arrays.asList(MyError.builder().status(422).code(RegisterFormErrorCode.SHORT_PASSWORD)
                        .title("password is too short").build());

                //when
                List<MyError> actual = registrationService.validateRequest(userDto);

                //then
                assertIterableEquals(expected, actual);
            }

            @Test
            @DisplayName("Password long test")
            public void passworLongTest() {
                //given
                UserDTO userDto = UserDTO.builder().name("Maciek123").email("Maciek123@gmail.com").
                        password("AlamaKota#2132gfdge").build();
                List<MyError> expected = Arrays.asList(MyError.builder().status(422).code(RegisterFormErrorCode.LONG_PASSWORD)
                        .title("password is too long").build());

                //when
                List<MyError> actual = registrationService.validateRequest(userDto);

                //then
                assertIterableEquals(expected, actual);
            }

            @Test
            @DisplayName("Password weak and short test")
            public void passwordWeakAndShortTest() {
                //given
                UserDTO userDTO = UserDTO.builder().name("Maciek123").email("Maciek123@gmail.com").password("A").build();
                List<MyError> expected = Arrays.asList(
                        MyError.builder().status(422).code(RegisterFormErrorCode.WEAK_PASSWORD)
                                .title("password is too weak").build(),
                        MyError.builder().status(422).code(RegisterFormErrorCode.SHORT_PASSWORD)
                                .title("password is too short").build());

                //when
                List<MyError> actual = registrationService.validateRequest(userDTO);

                //then
                assertIterableEquals(expected, actual);
            }

            @Test
            @DisplayName("Password weak and long test")
            public void passwordWeakAndLongTest() {
                //given
                UserDTO userDTO = UserDTO.builder().name("Maciek123").email("Maciek123@gmail.com").
                        password("AlaMaKota12321321321312").build();
                List<MyError> expected = Arrays.asList(
                        MyError.builder().status(422).code(RegisterFormErrorCode.WEAK_PASSWORD)
                                .title("password is too weak").build(),
                        MyError.builder().status(422).code(RegisterFormErrorCode.LONG_PASSWORD)
                                .title("password is too long").build());

                //when
                List<MyError> actual = registrationService.validateRequest(userDTO);

                //then
                assertIterableEquals(expected, actual);
            }
        }

        @Nested
        class SuccesValidationTests{

            @Test
            @DisplayName("All 3 parameters are correct")
            public void correctDataTest(){
                //given
                UserDTO userDTO = UserDTO.builder().name("Maciek123").email("maciek123@gmail.com")
                        .password("AlamaKota123#").build();
                List<MyError> expected = new ArrayList<>();

                //when
                List<MyError> actual = registrationService.validateRequest(userDTO);

                //then
                assertIterableEquals(expected,actual);
            }
        }
    }

    @Nested
    class ValidateSaveUserMethodTests{
        //TODO write this tests ( saveUser returns void)
    }
}
