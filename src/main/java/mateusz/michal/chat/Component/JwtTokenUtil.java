package mateusz.michal.chat.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${SECRETKEY}")
    private String SECRET_KEY;

    public String getUsernameFromToken(String token){
        return JWT.decode(token).getSubject();
    }

    public String generateToken(String username){
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        String token = JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .sign(algorithm);
        return token;
    }

    public DecodedJWT decodeAndVerifyJwt(String token){
        DecodedJWT decodedJWT = null;
            try{
                JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build();
                decodedJWT = verifier.verify(token);
                } catch (JWTVerificationException exception){
                    exception.printStackTrace();
                }
                return decodedJWT;
    }
}
