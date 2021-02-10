package mateusz.michal.chat.User.Controller;

import mateusz.michal.chat.Structure.RespondStructure.IJsonResponse;
import mateusz.michal.chat.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<IJsonResponse> getUsersListByPage(Pageable page){
        return userService.getUserListByPage(page);
    }
}
