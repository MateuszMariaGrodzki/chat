package mateusz.michal.chat.Controller;

import mateusz.michal.chat.Model.User;
import mateusz.michal.chat.Service.RegistrationService;
import mateusz.michal.chat.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping(value = "/register",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNewUser(@RequestBody User user){
        try {
            registrationService.saveUserToDatabase(user);
            return ResponseEntity.ok("Registered");
        } catch(Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
