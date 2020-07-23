package mateusz.michal.chat.Controller;

import mateusz.michal.chat.Model.JwtTokenRequest;
import mateusz.michal.chat.Model.JwtTokenResponse;
import mateusz.michal.chat.Service.JwtAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    JwtAuthenticationService jwtAuthenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtTokenResponse> createAuthenticationToken(@RequestBody JwtTokenRequest jwtTokenRequest){
        return ResponseEntity.ok(jwtAuthenticationService.authenticate(jwtTokenRequest));
    }
}
