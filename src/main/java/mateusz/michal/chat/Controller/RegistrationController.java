package mateusz.michal.chat.Controller;

import mateusz.michal.chat.Model.JsonRespond;
import mateusz.michal.chat.Model.User;
import mateusz.michal.chat.Service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping(value = "/register",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonRespond> createNewUser(@RequestBody User user){
        String answer = registrationService.saveUserToDatabase(user);
        JsonRespond error = new JsonRespond(answer);
        return ResponseEntity.ok(error);
    }
}
