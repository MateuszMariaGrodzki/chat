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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.regex.Matcher;
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
        return (name.startsWith(" ") || name.endsWith(" ") || isNameHasSpecialCharacters(name));
    }

    private boolean isEmailNotPresent(String email){
        return email.equals("");
    }

    private boolean isPassworNotPresent(String password){
        return password.equals("");
    }

    private boolean isNameHasSpecialCharacters(String name){
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

    private boolean isEmailIncorrect(String email){
        Pattern pattern = Pattern.compile("[a-zA-Z]+[\\.a-zA-Z0-9]*@[a-zA-Z0-9]+\\.[a-z]{2,}[a-z]*");
        return !pattern.matcher(email).matches();
    }

    public boolean isPasswordIncorrect(String password) {
        Pattern pattern = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])" +
                "(?=.*[\\!\\@\\#\\$\\%\\^\\&\\*])(?!=.*\\s).{8,15}");
        return !pattern.matcher(password).matches();
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

    public ResponseEntity<JsonRespond> getResponseForUserRegistration(UserDTO userDTO){
        RegisterFormErrorCode errorCode = saveUserToDatabase(userDTO);
        if (errorCode.equals(RegisterFormErrorCode.REGISTERED)){
            return ResponseEntity.ok(new JsonRespond(null,true));
        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new JsonRespond(errorCode,false));
        }
    }
}
