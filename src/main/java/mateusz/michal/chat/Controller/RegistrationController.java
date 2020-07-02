package mateusz.michal.chat.Controller;

import mateusz.michal.chat.Model.User;
import mateusz.michal.chat.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController()
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RegistrationController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public void registration(){}

    @PostMapping(value = "/register",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createNewUser(@RequestBody User user){
        userService.saveUser(user);
    }
}
