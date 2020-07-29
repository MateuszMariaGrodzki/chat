package mateusz.michal.chat.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import mateusz.michal.chat.Model.JwtTokenRequest;
import mateusz.michal.chat.Model.JwtTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtAuthenticationService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Value("${SECRETKEY}")
    private String SECRET_KEY;

    public JwtTokenResponse authenticate(JwtTokenRequest jwtTokenRequest){
        if(isNameMissing(jwtTokenRequest.getUsername())){
            return new JwtTokenResponse("username_missing",null);
        } else if (isPasswordMissing(jwtTokenRequest.getPassword())){
            return new JwtTokenResponse("password_missing",null);
        }
        try {
            UserDetails userDetails = provideUserDetailsFromLoginForm(jwtTokenRequest.getUsername(),
                    jwtTokenRequest.getPassword());
            String token = generateToken(userDetails.getUsername());
            return new JwtTokenResponse(null,token);
        } catch (BadCredentialsException e){
            return new JwtTokenResponse("bad_credentials",null);
        }
    }

    private String generateToken(String username){
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        String token = JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .sign(algorithm);
        return token;
    }

    private UserDetails provideUserDetailsFromLoginForm(String username, String password){
            Authentication authentication = authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return (UserDetails) authentication.getPrincipal();
    }

    private boolean isNameMissing(String name){
        return name.equals("");
    }

    private boolean isPasswordMissing(String password){
        return password.equals("");
    }

}
