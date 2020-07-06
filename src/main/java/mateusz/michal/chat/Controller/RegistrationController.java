package mateusz.michal.chat.Controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import mateusz.michal.chat.Model.JsonRespond;
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
    public ResponseEntity<JsonRespond> createNewUser(@RequestBody User user){
        try {
            registrationService.saveUserToDatabase(user);
            JsonRespond error = new JsonRespond("registered");
            return ResponseEntity.ok(error);
        } catch(Exception e){
            return ResponseEntity.ok(new JsonRespond(e.getMessage()));
        }
    }
}
