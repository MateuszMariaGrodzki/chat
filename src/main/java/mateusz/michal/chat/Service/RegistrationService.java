package mateusz.michal.chat.Service;

import com.sun.istack.NotNull;
import mateusz.michal.chat.Model.JsonRespond;
import mateusz.michal.chat.Model.Role;
import mateusz.michal.chat.Model.User;
import mateusz.michal.chat.Model.UserDTO;
import mateusz.michal.chat.Repository.RoleRepository;
import mateusz.michal.chat.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class RegistrationService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    private User createUser(UserDTO userDTO){
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return user;
    }

    private boolean isNameNotPresent(String name){
        return name.equals("");
    }

    private boolean isEmailNotPresent(String email){
        return email.equals("");
    }

    private boolean isPassworNotPresent(String password){
        return password.equals("");
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

    public String saveUserToDatabase(@NotNull UserDTO userDTO) {
        if(isNameNotPresent(userDTO.getName())){
            return "name_missing";
        } else if(isEmailNotPresent(userDTO.getEmail())){
            return "email_missing";
        } else if(isPassworNotPresent(userDTO.getPassword())){
            return "password_missing";
        } else if (isUserInDatabaseByName(userDTO.getName())){
            return "name_occupied";
        } else if(isUserInDatabaseByEmail(userDTO.getEmail())){
            return "email_occupied";
        } else if(isEmailIncorrect(userDTO.getEmail())){
            return "email_incorrect";
        } else if(isPasswordIncorrect(userDTO.getPassword())){
            return "weak_password";
        } else {
            User userToSave = createUser(userDTO);
            userRepository.save(userToSave);
            return "registered";
        }
    }

    public JsonRespond getResponseForUserRegistration(UserDTO userDTO){
        String errorCode = saveUserToDatabase(userDTO);
        if (errorCode.equals("registered")){
            return new JsonRespond(null,true);
        } else {
            return new JsonRespond(errorCode,false);
        }
    }
}
