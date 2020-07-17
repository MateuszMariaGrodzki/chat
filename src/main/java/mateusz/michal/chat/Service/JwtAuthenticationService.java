package mateusz.michal.chat.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthenticationService {

    @Autowired
    AuthenticationManager authenticationManager;
}
