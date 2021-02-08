package mateusz.michal.chat.Registration.Controller;

import mateusz.michal.chat.Structure.RespondStructure.IJsonResponse;
import mateusz.michal.chat.Registration.Model.UserDTO;
import mateusz.michal.chat.Registration.Service.RegistrationService;
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
    public ResponseEntity<IJsonResponse> createNewUser(@RequestBody UserDTO userDto){
        return registrationService.getResponseForUserRegistration(userDto);
    }
}
