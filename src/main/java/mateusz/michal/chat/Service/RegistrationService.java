package mateusz.michal.chat.Service;

import com.sun.istack.NotNull;
import mateusz.michal.chat.Model.*;
import mateusz.michal.chat.Repository.RoleRepository;
import mateusz.michal.chat.Repository.UserRepository;
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

    @Autowired
    JsonFactory jsonFactory;

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

    private boolean isNameNotPresent(String name){
        return name.equals("");
    }

    private boolean isNameIncorrect(String name){
        return (name.startsWith(" ") || name.endsWith(" ") || hasNameSpecialCharacters(name));
    }

    private boolean isEmailNotPresent(String email){
        return email.equals("");
    }

    private boolean isPassworNotPresent(String password){
        return password.equals("");
    }

    private boolean hasNameSpecialCharacters(String name){
        for(char c : name.toCharArray()){
            if(!(Character.isLetterOrDigit(c))){
                return true;
            }
        }
        return false;
    }

    private boolean isNameNull(String name){
        return name == null;
    }

    private boolean isEmailNull(String email){
        return email == null;
    }

    private boolean isPasswordNull(String password){
        return password == null;
    }

    private boolean isUserInDatabaseByName(String name){
        Optional<User> user = Optional.ofNullable(userRepository.findByName(name));
        return user.isPresent();
    }

    private boolean isUserInDatabaseByEmail(String email){
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        return user.isPresent();
    }

    private boolean isEmailIncorrect(String email){
        Pattern pattern = Pattern.compile("[a-zA-Z]+[\\.a-zA-Z0-9]*@[a-zA-Z0-9]+\\.[a-z]{2,}[a-z]*");
        return !pattern.matcher(email).matches();
    }

    public boolean isPasswordIncorrect(String password) {
        Pattern pattern = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])" +
                "(?=.*[\\!\\@\\#\\$\\%\\^\\&\\*])(?!=.*\\s).{8,15}");
        return !pattern.matcher(password).matches();
    }

    private List<MyError> validateUserName(String name){
        List<MyError> errors = new ArrayList<>();
        if(isNameNull(name)){
            errors.add(new MyError(400,RegisterFormErrorCode.NAME_NULL,
                    "parameter name is null"));
        } else {
            if(isNameNotPresent(name)){
                errors.add(new MyError(422,RegisterFormErrorCode.NAME_MISSING,
                        "parameter name isn't present"));
            }
            if(isUserInDatabaseByName(name)){
                errors.add(new MyError(422,RegisterFormErrorCode.NAME_OCCUPIED,
                        "there is user with that name in database"));
            }
            if(isNameIncorrect(name)){
                errors.add(new MyError(422,RegisterFormErrorCode.NAME_INCORRECT,
                        "there are some not allowed characters in name"));
            }
        }
        return errors;
    }

    private List<MyError> validateUserEmail(String email){
        List<MyError> errors = new ArrayList<>();
        if (isEmailNull(email)){
            errors.add(new MyError(400,RegisterFormErrorCode.EMAIL_NULL,
                    "parameter email is null"));
        } else {
            if(isEmailNotPresent(email)){
                errors.add(new MyError(422,RegisterFormErrorCode.EMAIL_MISSING,
                        "parameter email isn't present"));
            }
            if(isUserInDatabaseByEmail(email)){
                errors.add(new MyError(422,RegisterFormErrorCode.EMAIL_OCCUPIED,
                        "there is user with that email in database"));
            }
            if(isEmailIncorrect(email)){
                errors.add(new MyError(422,RegisterFormErrorCode.EMAIL_INCORRECT,
                        "email has bad format"));
            }
        }
        return errors;
    }

    private List<MyError> validateRegistrationRequest(UserDTO userDTO){
        List<MyError> errors = new ArrayList<>();
        errors.addAll(validateUserName(userDTO.getName()));
        errors.addAll(validateUserEmail(userDTO.getEmail()));
        if(isPasswordNull(userDTO.getPassword())){
            errors.add(new MyError(400, RegisterFormErrorCode.PASSWORD_NULL,
                    "parameter password is null"));
            userDTO.setPassword("");
        }
        if(isPassworNotPresent(userDTO.getPassword())){
            errors.add(new MyError(422,RegisterFormErrorCode.PASSWORD_MISSING,
                    "parameter password isn't present"));
        }
        if(isPasswordIncorrect(userDTO.getPassword())){
            errors.add(new MyError(422,RegisterFormErrorCode.WEAK_PASSWORD,
                    "password is too weak"));
        }
        return errors;
    }

    public RegisterFormErrorCode saveUserToDatabase(@NotNull UserDTO userDTO) {
        if(isNameNotPresent(userDTO.getName())){
            return RegisterFormErrorCode.NAME_MISSING;
        } else if(isEmailNotPresent(userDTO.getEmail())){
            return RegisterFormErrorCode.EMAIL_MISSING;
        } else if(isPassworNotPresent(userDTO.getPassword())){
            return RegisterFormErrorCode.PASSWORD_MISSING;
        } else if(isNameIncorrect(userDTO.getName())) {
            return RegisterFormErrorCode.NAME_INCORRECT;
        } else if (isUserInDatabaseByName(userDTO.getName())){
            return RegisterFormErrorCode.NAME_OCCUPIED;
        } else if(isUserInDatabaseByEmail(userDTO.getEmail())){
            return RegisterFormErrorCode.EMAIL_OCCUPIED;
        } else if(isEmailIncorrect(userDTO.getEmail())){
            return RegisterFormErrorCode.EMAIL_INCORRECT;
        } else if(isPasswordIncorrect(userDTO.getPassword())){
            return RegisterFormErrorCode.WEAK_PASSWORD;
        } else {
            User userToSave = createUser(userDTO);
            userRepository.save(userToSave);
            return RegisterFormErrorCode.REGISTERED;
        }
    }

    public ResponseEntity<IJsonResponse> getResponseForUserRegistration(UserDTO userDTO){
        List<MyError> errors = validateRegistrationRequest(userDTO);
        if (errors.size() == 0){
            User user = createUser(userDTO);
            userRepository.save(user);
            return ResponseEntity.ok(
                    jsonFactory.createResponse(ResponseEnum.DATA,null,
                            new SimpleDataResponse("user has been succesfully registered and saved into database"),
                            null));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonFactory.createResponse(ResponseEnum.ERROR,
                    errors,null,null));
        }

    }
}
