package mateusz.michal.chat.Controller;

import mateusz.michal.chat.Model.JwtTokenRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @PostMapping("/authenticate")
    public ResponseEntity<String> createAuthenticationToken(@RequestBody JwtTokenRequest jwtTokenRequest){
        return ResponseEntity.ok(jwtTokenRequest.getUsername());
    }
}
