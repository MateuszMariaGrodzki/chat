package mateusz.michal.chat.Service;

import mateusz.michal.chat.Component.JwtTokenUtil;
import mateusz.michal.chat.Model.JwtAuthenticationErrorCode;
import mateusz.michal.chat.Model.JwtTokenRequest;
import mateusz.michal.chat.Model.JwtTokenResponse;
import mateusz.michal.chat.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class AuthenticationService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    CookieService cookieService;

    @Value("${SECRETKEY}")
    private String SECRET_KEY;

    public ResponseEntity<JwtTokenResponse> authenticate(JwtTokenRequest jwtTokenRequest, HttpServletResponse response){
        if(isNameMissing(jwtTokenRequest.getName())){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new JwtTokenResponse(JwtAuthenticationErrorCode.NAME_MISSING,
                    null,null,null));
        } else if (isPasswordMissing(jwtTokenRequest.getPassword())){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new JwtTokenResponse(JwtAuthenticationErrorCode.PASSWORD_MISSING,
                    null, null,null));
        }
        try {
            UserDetails userDetails = provideUserDetailsFromLoginForm(jwtTokenRequest.getName(),
                    jwtTokenRequest.getPassword());
            String token = jwtTokenUtil.generateToken(userDetails.getUsername());
            User user = userRepository.findByName(jwtTokenRequest.getName());
            response.addCookie(cookieService.generateRefreshCookie(token));
            return ResponseEntity.ok(new JwtTokenResponse(null,
                    token,user.getName(),user.getEmail()));
        } catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new JwtTokenResponse(JwtAuthenticationErrorCode.BAD_CREDENTIALS,
                    null,null,null));
        }
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

    private boolean isNameNull(String name){
        return name == null;
    }

    private boolean isPasswordNull(String password){
        return password == null;
    }

}
