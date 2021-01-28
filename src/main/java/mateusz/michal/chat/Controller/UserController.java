package mateusz.michal.chat.Controller;

import mateusz.michal.chat.Model.IJsonResponse;
import mateusz.michal.chat.Model.MainPageUserProfilesDTO;
import mateusz.michal.chat.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public ResponseEntity<IJsonResponse> getUserInfo(HttpServletRequest request){
        return userService.loadUserProfilDTOFromDataBaseByJwtToken(request);
    }

    @GetMapping("/users")
    public ResponseEntity<MainPageUserProfilesDTO> getUsersListToPage(@RequestParam(defaultValue = "1") int page){
        return userService.getUserListByPage(page);
    }
}
