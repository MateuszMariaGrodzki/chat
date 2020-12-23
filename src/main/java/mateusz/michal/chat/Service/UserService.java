package mateusz.michal.chat.Service;

import lombok.AllArgsConstructor;
import mateusz.michal.chat.Component.JwtTokenUtil;
import mateusz.michal.chat.Model.Role;
import mateusz.michal.chat.Model.User;
import mateusz.michal.chat.Model.UserDTO;
import mateusz.michal.chat.Model.UserProfilDTO;
import mateusz.michal.chat.Repository.RoleRepository;
import mateusz.michal.chat.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User findByName(String name){
        return userRepository.findByName(name);
    }

    public User findById(int id){ return userRepository.findById(id);}

    public User saveUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    public UserProfilDTO loadUserProfilDTOFromDataBaseByJwtToken(String token){
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User user = findByName(username);
        return new UserProfilDTO(user.getName(),user.getEmail());
    }
}
