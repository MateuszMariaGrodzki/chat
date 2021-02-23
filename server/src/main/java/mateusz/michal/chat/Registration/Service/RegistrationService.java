package mateusz.michal.chat.Registration.Service;

import com.sun.istack.NotNull;
import mateusz.michal.chat.Model.*;
import mateusz.michal.chat.Registration.Model.RegisterFormErrorCode;
import mateusz.michal.chat.Registration.Model.UserDTO;
import mateusz.michal.chat.Repository.RoleRepository;
import mateusz.michal.chat.Structure.RespondStructure.*;
import mateusz.michal.chat.User.Model.User;
import mateusz.michal.chat.User.Repository.UserRepository;
import mateusz.michal.chat.User.Service.SlugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

@Service
public class RegistrationService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SlugService slugService;

    public ResponseEntity<IJsonResponse> getResponseForUserRegistration(UserDTO userDTO){
        List<MyError> errors = validateRequest(userDTO);
        if (errors.size() == 0){
            User user = createUser(userDTO);
            userRepository.save(user);
            return ResponseEntity.ok(
                    JsonResponseFactory.createResponse(
                            new SimpleDataResponse("user has been succesfully registered and saved in database")));
        }
        for(MyError error : errors){
            if(error.getStatus() == 400){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                        body(JsonResponseFactory.createResponse(errors));
            }
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).
                body(JsonResponseFactory.createResponse(errors));
    }

    private User createUser(UserDTO userDTO){
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setSlug(slugService.generateUniqueSlug(user.getName()));
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return user;
    }

    // method that validate request
    private List<MyError> validateRequest(UserDTO userDTO){
        List<MyError> errors = new ArrayList<>();
        errors.addAll(validateUserName(userDTO.getName()));
        errors.addAll(validateUserEmail(userDTO.getEmail()));
        errors.addAll(validateUserPassword(userDTO.getPassword()));
        return errors;
    }

    // methods that validate each property
    private List<MyError> validateUserName(String name){
        List<MyError> errors = new ArrayList<>();
        if(isNull(name)){
            errors.add(new MyError(400, RegisterFormErrorCode.NAME_NULL,
                    "parameter name is null"));
        } else {
            if(isEmpty(name)){
                errors.add(new MyError(422,RegisterFormErrorCode.NAME_MISSING,
                        "parameter name isn't present"));
            }
            if(isUserInDatabaseByName(name)){
                errors.add(new MyError(422,RegisterFormErrorCode.NAME_OCCUPIED,
                        "In database exist user with that name"));
            }
            if(isNameIncorrect(name)){
                errors.add(new MyError(422,RegisterFormErrorCode.NAME_INCORRECT,
                        "name can only have letters and digits"));
            }
        }
        return errors;
    }

    private List<MyError> validateUserEmail(String email){
        List<MyError> errors = new ArrayList<>();
        if (isNull(email)){
            errors.add(new MyError(400,RegisterFormErrorCode.EMAIL_NULL,
                    "parameter email is null"));
        } else {
            if(isEmpty(email)){
                errors.add(new MyError(422,RegisterFormErrorCode.EMAIL_MISSING,
                        "parameter email isn't present"));
            }
            if(isUserInDatabaseByEmail(email)){
                errors.add(new MyError(422,RegisterFormErrorCode.EMAIL_OCCUPIED,
                        "In database exist user with that email"));
            }
            if(!isEmailCorrect(email)){
                errors.add(new MyError(422,RegisterFormErrorCode.EMAIL_INCORRECT,
                        "email has bad format"));
            }
        }
        return errors;
    }

    private List<MyError> validateUserPassword(String password){
        List<MyError> errors = new ArrayList<>();
        if(isNull(password)){
            errors.add(new MyError(400, RegisterFormErrorCode.PASSWORD_NULL,
                    "parameter password is null"));
        } else {
            if(isEmpty(password)){
                errors.add(new MyError(422,RegisterFormErrorCode.PASSWORD_MISSING,
                        "parameter password isn't present"));
            }
            if(!isPasswordStrong(password)){
                errors.add(new MyError(422,RegisterFormErrorCode.WEAK_PASSWORD,
                        "password is too weak"));
            }
            if(isPasswordToShort(password)){
                errors.add(new MyError(422,RegisterFormErrorCode.SHORT_PASSWORD,
                        "password is too short"));
            }
            if(isPassworToLong(password)){
                errors.add(new MyError(422,RegisterFormErrorCode.LONG_PASSWORD,
                        "password is too long"));
            }
        }
        return errors;
    }

    // methods that validate single elements of properties
    private boolean isEmpty(String property){
        return property.equals("");
    }

    private boolean isNull(String property){
        return property == null;
    }

    private boolean isNameIncorrect(String name){
        return (name.startsWith(" ") || name.endsWith(" ") || hasNameSpecialCharacters(name));
    }

    private boolean hasNameSpecialCharacters(String name){
        for(char c : name.toCharArray()){
            if(!(Character.isLetterOrDigit(c))){
                return true;
            }
        }
        return false;
    }

    private boolean isUserInDatabaseByName(String name){
        Optional<User> user = Optional.ofNullable(userRepository.findByName(name));
        return user.isPresent();
    }

    private boolean isUserInDatabaseByEmail(String email){
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        return user.isPresent();
    }

    private boolean isEmailCorrect(String email){
        Pattern pattern = Pattern.compile("[a-zA-Z]+[\\.a-zA-Z0-9]*@[a-zA-Z0-9]+\\.[a-z]{2,}[a-z]*");
        return pattern.matcher(email).matches();
    }

    private boolean isPasswordStrong(String password) {
        Pattern pattern = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])" +
                "(?=.*[\\!\\@\\#\\$\\%\\^\\&\\*]).{0,}");
        return pattern.matcher(password).matches();
    }

    private boolean isPasswordToShort(String password){
        return password.length() < 8;
    }

    private boolean isPassworToLong(String password){
        return password.length() > 15;
    }
    
    // TODO delete this after test refactoring
    public RegisterFormErrorCode saveUserToDatabase(@NotNull UserDTO userDTO) {
        if(isEmpty(userDTO.getName())){
            return RegisterFormErrorCode.NAME_MISSING;
        } else if(isEmpty(userDTO.getEmail())){
            return RegisterFormErrorCode.EMAIL_MISSING;
        } else if(isEmpty(userDTO.getPassword())){
            return RegisterFormErrorCode.PASSWORD_MISSING;
        } else if(isNameIncorrect(userDTO.getName())) {
            return RegisterFormErrorCode.NAME_INCORRECT;
        } else if (isUserInDatabaseByName(userDTO.getName())){
            return RegisterFormErrorCode.NAME_OCCUPIED;
        } else if(isUserInDatabaseByEmail(userDTO.getEmail())){
            return RegisterFormErrorCode.EMAIL_OCCUPIED;
        } else if(isEmailCorrect(userDTO.getEmail())){
            return RegisterFormErrorCode.EMAIL_INCORRECT;
        } else if(isPasswordStrong(userDTO.getPassword())){
            return RegisterFormErrorCode.WEAK_PASSWORD;
        } else {
            User userToSave = createUser(userDTO);
            userRepository.save(userToSave);
            return RegisterFormErrorCode.REGISTERED;
        }
    }
}
