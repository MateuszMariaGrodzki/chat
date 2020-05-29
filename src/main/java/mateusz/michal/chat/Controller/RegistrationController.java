package mateusz.michal.chat.Controller;

import mateusz.michal.chat.Model.User;
import mateusz.michal.chat.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public void registration(){}

    @PostMapping("/register")
    public void createNewUser(@RequestParam(name = "user") User user){
        userService.saveUser(user);
    }
}
