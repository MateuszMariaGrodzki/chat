package mateusz.michal.chat.Service;


import mateusz.michal.chat.Component.JwtTokenUtil;
import mateusz.michal.chat.Model.*;
import mateusz.michal.chat.Repository.RoleRepository;
import mateusz.michal.chat.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public User loadUserByUserName(String name) throws UsernameNotFoundException{
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("User don't exists");
        } else {
            return user;
        }
    }

    public User findById(int id){ return userRepository.findById(id);}

    public User saveUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    public ResponseEntity<IJsonResponse> loadUserProfilDTOFromDataBaseByJwtToken(HttpServletRequest request){
        Cookie cookie = cookieService.getTokenCookieFromCookies(request.getCookies());
        if(cookie == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    JsonResponseFactory.createResponse(ResponseEnum.ERROR,
                            Arrays.asList(new MyError(401,UserProfilErrorCode.UNAUTHORIZED_USER,
                                    "server dosen't recive cookie with token")),null,null)
            );
        } else {
            String username = jwtTokenUtil.getUsernameFromToken(cookie.getValue());
            if(username == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        JsonResponseFactory.createResponse(ResponseEnum.ERROR,
                                Arrays.asList(new MyError(400,UserProfilErrorCode.USERNAME_NULL,
                                        "decoded token has null username")),null,null)
                );
            } else {
                User user;
                try{
                    user = loadUserByUserName(username);
                    return ResponseEntity.ok(
                            JsonResponseFactory.createResponse(ResponseEnum.DATA,
                                    null,new UserProfilDTO(user.getName(),user.getEmail()),null)
                    );
                } catch (UsernameNotFoundException e){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                            JsonResponseFactory.createResponse(ResponseEnum.ERROR,
                                    Arrays.asList(new MyError(400,UserProfilErrorCode.UNKNOWN_USER,
                                            "token is valid but user is not registered")),null,null)
                    );
                }
            }
        }
    }

    public ResponseEntity<MainPageUserProfilesDTO> getUserListByPage(int page) {
        if(page <= 0) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).
                    body(new MainPageUserProfilesDTO(UsersViewOnPageErrorCodes.INCORRECT_PAGE, null));
        }
        List<User> users = userRepository.findByIdBetween(10*(page - 1) + 1, 10*page);
        List<UserNameAndSlug> mappedUsers = mapUserNameAndSlugFromUser(users);
        return ResponseEntity.ok(new MainPageUserProfilesDTO(null,mappedUsers));
    }

    private List<UserNameAndSlug> mapUserNameAndSlugFromUser(List<User> users){
        List<UserNameAndSlug> result = new ArrayList<>();
        for (User user : users){
            result.add(new UserNameAndSlug(user.getName(),user.getSlug()));
        }
        return result;
    }
}
