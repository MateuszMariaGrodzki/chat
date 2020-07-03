package mateusz.michal.chat.Controller;

import mateusz.michal.chat.Model.User;
import mateusz.michal.chat.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/register",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createNewUser(@RequestBody User user){
        userService.saveUser(user);
    }
}
