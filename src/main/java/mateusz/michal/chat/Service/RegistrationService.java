package mateusz.michal.chat.Service;

import com.sun.istack.NotNull;
import mateusz.michal.chat.Model.Role;
import mateusz.michal.chat.Model.User;
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

    private User createUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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


    private boolean isUserNotInDatabaseByName(String name){
        Optional<User> user = Optional.ofNullable(userRepository.findByName(name));
        return !user.isPresent();
    }

    private boolean isUserNotInDatabaseByEmail(String email){
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        return !user.isPresent();
    }

    private boolean isEmailIncorrect(String email){
        Pattern pattern = Pattern.compile("[a-zA-Z]+[\\.a-zA-Z0-9]*@[a-zA-Z0-9]+\\.[a-z]{2,}[a-z]*");
        return ! pattern.matcher(email).matches();
    }

    public void saveUserToDatabase(@NotNull User user) throws Exception {
        if(isUserNotInDatabaseByName(user.getName())) {
            User userToSave = createUser(user);
            userRepository.save(userToSave);
        } else {
            throw new Exception("Podane imię lub email jest już zajęte");
        }
    }
}
