package mateusz.michal.chat.Controller;

import mateusz.michal.chat.Component.JwtTokenUtil;
import mateusz.michal.chat.Model.JwtTokenRequest;
import mateusz.michal.chat.Model.JwtTokenResponse;
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

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtTokenResponse> createAuthenticationToken(@RequestBody JwtTokenRequest jwtTokenRequest,
                                                                      HttpServletResponse response){
        return authenticationService.authenticate(jwtTokenRequest, response);
    }
}
