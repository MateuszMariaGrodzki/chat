package mateusz.michal.chat.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${SECRETKEY}")
    private String SECRET_KEY;

    public String getUsernameFromToken(String token){
        String name = "";
        try {
            name = JWT.decode(token).getSubject();
        } catch(JWTDecodeException exception){
            return null;
        }
        return name;
    }

    public String generateToken(String username){
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        String token = JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .sign(algorithm);
        return token;
    }

    public boolean decodeAndVerifyJwt(String token, UserDetails userDetails){
       String usernameFromToken = getUsernameFromToken(token);
       return usernameFromToken.equals(userDetails.getUsername());
    }
}
