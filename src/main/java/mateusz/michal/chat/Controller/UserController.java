package mateusz.michal.chat.Controller;

import mateusz.michal.chat.Model.JwtToken;
import mateusz.michal.chat.Model.UserProfilDTO;
import mateusz.michal.chat.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity<UserProfilDTO> getUserInfo(@RequestBody JwtToken jwtToken){
        return ResponseEntity.ok(userService.loadUserProfilDTOFromDataBaseByJwtToken(jwtToken.getToken()));
    }
}
