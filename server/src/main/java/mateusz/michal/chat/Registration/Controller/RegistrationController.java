package mateusz.michal.chat.Registration.Controller;

import mateusz.michal.chat.Structure.RespondStructure.IJsonResponse;
import mateusz.michal.chat.Registration.Model.UserDTO;
import mateusz.michal.chat.Registration.Service.RegistrationService;
import mateusz.michal.chat.Structure.RespondStructure.JsonResponseFactory;
import mateusz.michal.chat.Structure.RespondStructure.MyError;
import mateusz.michal.chat.Structure.RespondStructure.SimpleDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping(value = "/register",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IJsonResponse> createNewUser(@RequestBody UserDTO userDto){
        List<MyError> errors = registrationService.validateRequest(userDto);
        if(errors.size() ==0 ){
            registrationService.saveUser(userDto);
            return ResponseEntity.ok(
                    JsonResponseFactory.createResponse(
                            new SimpleDataResponse("user has been succesfully registered and saved in database")));
        }
        for(MyError error : errors){
            if(error.getStatus() == 400){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                        body(JsonResponseFactory.createResponse(errors));
            }
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).
                body(JsonResponseFactory.createResponse(errors));
    }
}
