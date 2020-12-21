package mateusz.michal.chat.Component;

import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {

    @Value("${SECRETKEY}")
    private String SECRET_KEY;

    public String getUserNameFromToken(String token){
        return JWT.decode(token).getSubject();
    }
}
