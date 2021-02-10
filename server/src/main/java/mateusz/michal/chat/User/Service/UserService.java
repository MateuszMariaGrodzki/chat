package mateusz.michal.chat.User.Service;

import mateusz.michal.chat.Authorization.Service.CookieService;
import mateusz.michal.chat.Component.JwtTokenUtil;
import mateusz.michal.chat.Structure.RespondStructure.MyError;
import mateusz.michal.chat.Structure.RespondStructure.ResponseEnum;
import mateusz.michal.chat.User.Model.*;
import mateusz.michal.chat.User.Repository.UserRepository;
import mateusz.michal.chat.Structure.RespondStructure.IJsonResponse;
import mateusz.michal.chat.Structure.RespondStructure.JsonResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    CookieService cookieService;

    public User loadUserByUserName(String name) throws UsernameNotFoundException{
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("User don't exists");
        } else {
            return user;
        }
    }

    public ResponseEntity<IJsonResponse> loadUserProfilDTOFromDataBaseByJwtToken(HttpServletRequest request){
        Cookie cookie = cookieService.getTokenCookieFromCookies(request.getCookies());
        if(cookie == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    JsonResponseFactory.createResponse(Arrays.asList(
                            new MyError(401, UserProfilErrorCode.UNAUTHORIZED_USER,
                                    "server dosen't recive cookie with token"))));
        }
        String username = jwtTokenUtil.getUsernameFromToken(cookie.getValue());
        if(username == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    JsonResponseFactory.createResponse(Arrays.asList(
                            new MyError(400,UserProfilErrorCode.USERNAME_NULL,
                                    "decoded token has null username"))));
        }
        User user;
        try{
            user = loadUserByUserName(username);
            return ResponseEntity.ok(
                    JsonResponseFactory.createResponse(new UserProfilDTO(user.getName(),user.getEmail())));
        } catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    JsonResponseFactory.createResponse(Arrays.asList(
                            new MyError(400,UserProfilErrorCode.UNKNOWN_USER,
                                    "token is valid but user is not registered"))));
        }
    }

    public ResponseEntity<IJsonResponse> getUserListByPage(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        List<UserNameAndSlug> mappedUsers = mapUserNameAndSlugFromUser(users);
        int maxpages = users.getTotalPages();
        return ResponseEntity.ok(JsonResponseFactory.createResponse(
                new MainPageUserProfilesDTO(mappedUsers),new PageMetadata(maxpages)));
    }

    private List<UserNameAndSlug> mapUserNameAndSlugFromUser(Page<User> users){
        List<UserNameAndSlug> result = new ArrayList<>();
        for (User user : users){
            result.add(new UserNameAndSlug(user.getName(),user.getSlug()));
        }
        return result;
    }
}
