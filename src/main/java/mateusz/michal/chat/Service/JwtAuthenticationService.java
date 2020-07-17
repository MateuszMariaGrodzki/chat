package mateusz.michal.chat.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthenticationService {

    @Autowired
    AuthenticationManager authenticationManager;

    private UserDetails provideUserDetailsFromLoginForm(String username, String password){
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(username,password));
        return (UserDetails) authentication.getPrincipal();
    }
}
