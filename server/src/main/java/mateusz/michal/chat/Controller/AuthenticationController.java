package mateusz.michal.chat.Controller;

import mateusz.michal.chat.Model.IJsonResponse;
import mateusz.michal.chat.Model.JwtTokenRequest;
import mateusz.michal.chat.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<IJsonResponse> createAuthenticationToken(@RequestBody JwtTokenRequest jwtTokenRequest,
                                                                   HttpServletResponse response){
        return authenticationService.authenticate(jwtTokenRequest, response);
    }
}
