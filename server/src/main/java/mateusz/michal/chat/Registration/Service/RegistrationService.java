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

    public void saveUser(UserDTO userDTO){
        User user = createUser(userDTO);
        userRepository.save(user);
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
    public List<MyError> validateRequest(UserDTO userDTO){
        List<MyError> errors = new ArrayList<>();
        errors.addAll(validateUserName(userDTO.getName()));
        errors.addAll(validateUserEmail(userDTO.getEmail()));
        errors.addAll(validateUserPassword(userDTO.getPassword()));
        return errors;
    }

    // methods that validate each property
    private List<MyError> validateUserName(String name){
        if(isNull(name)) {
            return Arrays.asList(new MyError(400, RegisterFormErrorCode.NAME_NULL,
                    "parameter name is null"));
        }
        if (isEmpty(name)) {
            return Arrays.asList(new MyError(422, RegisterFormErrorCode.NAME_MISSING,
                    "parameter name isn't present"));
        }
        List<MyError> errors = new ArrayList<>();
        if (isUserInDatabaseByName(name)) {
            errors.add(new MyError(422, RegisterFormErrorCode.NAME_OCCUPIED,
                    "In database exist user with that name"));
        }
        if (!isNameCorrect(name)) {
            errors.add(new MyError(422, RegisterFormErrorCode.NAME_INCORRECT,
                    "name can only have letters and digits"));
        }
        return errors;
    }

    private List<MyError> validateUserEmail(String email){
        if (isNull(email)){
            return Arrays.asList(new MyError(400,RegisterFormErrorCode.EMAIL_NULL,
                    "parameter email is null"));
        }
        if(isEmpty(email)){
            return Arrays.asList(new MyError(422,RegisterFormErrorCode.EMAIL_MISSING,
                    "parameter email isn't present"));
        }
        List<MyError> errors = new ArrayList<>();
        if(isUserInDatabaseByEmail(email)){
            errors.add(new MyError(422,RegisterFormErrorCode.EMAIL_OCCUPIED,
                    "In database exist user with that email"));
        }
        if(!isEmailCorrect(email)){
            errors.add(new MyError(422,RegisterFormErrorCode.EMAIL_INCORRECT,
                    "email has bad format"));
        }
        return errors;
    }

    private List<MyError> validateUserPassword(String password){
        if(isNull(password)){
            return Arrays.asList(new MyError(400, RegisterFormErrorCode.PASSWORD_NULL,
                    "parameter password is null"));
        }
        if(isEmpty(password)){
            return Arrays.asList(new MyError(422,RegisterFormErrorCode.PASSWORD_MISSING,
                    "parameter password isn't present"));
        }
        List<MyError> errors = new ArrayList<>();
        if(!isPasswordStrong(password)){
            errors.add(new MyError(422,RegisterFormErrorCode.WEAK_PASSWORD,
                    "password is too weak"));
        }
        if(isPasswordTooShort(password)){
            errors.add(new MyError(422,RegisterFormErrorCode.SHORT_PASSWORD,
                    "password is too short"));
        }
        if(isPassworTooLong(password)){
            errors.add(new MyError(422,RegisterFormErrorCode.LONG_PASSWORD,
                    "password is too long"));
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

    private boolean isNameCorrect(String name){
        return !(name.startsWith(" ") || name.endsWith(" ") || hasNameSpecialCharacters(name));
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
        return password.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\!\\@\\#\\$\\%\\^\\&\\*]).{0,}");
    }

    private boolean isPasswordTooShort(String password){
        return password.length() < 8;
    }

    private boolean isPassworTooLong(String password){
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
        } else if(!isNameCorrect(userDTO.getName())) {
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
