package mateusz.michal.chat.Service;


import mateusz.michal.chat.Component.JwtTokenUtil;
import mateusz.michal.chat.Model.*;
import mateusz.michal.chat.Repository.RoleRepository;
import mateusz.michal.chat.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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

    @Autowired
    CookieService cookieService;

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

    public UserProfilDTO loadUserProfilDTOFromDataBaseByJwtToken(HttpServletRequest request){
        Cookie cookie = cookieService.getTokenCookieFromCookies(request.getCookies());
        if(cookie == null){
            return new UserProfilDTO();
        } else {
            String username = jwtTokenUtil.getUsernameFromToken(cookie.getValue());
            if(username == null){
                return new UserProfilDTO();
            } else {
                User user = findByName(username);
                return new UserProfilDTO(user.getName(),user.getEmail());
            }
        }
    }

   

    private List<UserNameAndSlug> mapUserNameAndSlugFromUser(List<User> users){
        List<UserNameAndSlug> result = new ArrayList<>();
        for (User user : users){
            result.add(new UserNameAndSlug(user.getName(),"slug"));
        }
        return result;
    }
}
