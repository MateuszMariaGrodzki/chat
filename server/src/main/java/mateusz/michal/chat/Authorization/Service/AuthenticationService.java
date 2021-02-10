package mateusz.michal.chat.Authorization.Service;

import mateusz.michal.chat.Authorization.Model.JwtAuthenticationErrorCode;
import mateusz.michal.chat.Authorization.Model.JwtTokenRequest;
import mateusz.michal.chat.Authorization.Model.JwtTokenResponse;
import mateusz.michal.chat.Component.JwtTokenUtil;
import mateusz.michal.chat.Structure.RespondStructure.MyError;
import mateusz.michal.chat.Structure.RespondStructure.ResponseEnum;
import mateusz.michal.chat.User.Model.User;
import mateusz.michal.chat.User.Service.UserService;
import mateusz.michal.chat.Structure.RespondStructure.IJsonResponse;
import mateusz.michal.chat.Structure.RespondStructure.JsonResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AuthenticationService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    CookieService cookieService;

    public ResponseEntity<IJsonResponse> authenticate(JwtTokenRequest jwtTokenRequest, HttpServletResponse response){
        List<MyError> errors = validateAuthenticationRequest(jwtTokenRequest);
        for (MyError error : errors){
            if (error.getStatus() == 400){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                        body(JsonResponseFactory.createResponse(ResponseEnum.ERROR,
                                errors,null,null));
            }
        }
        if(errors.size() != 0){
            return ResponseEntity.status(422).
                    body(JsonResponseFactory.createResponse(ResponseEnum.ERROR,
                            errors,null,null));
        }
      
        try {
            UserDetails userDetails = provideUserDetailsFromLoginForm(jwtTokenRequest.getName(),
                    jwtTokenRequest.getPassword());
            String token = jwtTokenUtil.generateToken(userDetails.getUsername());
            User user = userService.loadUserByUserName(jwtTokenRequest.getName());
            response.addCookie(cookieService.generateRefreshCookie(token));
            return ResponseEntity.ok(JsonResponseFactory.createResponse(ResponseEnum.DATA,
                    null,new JwtTokenResponse(token,
                            user.getName(),user.getEmail()),null));
        } catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(JsonResponseFactory.createResponse(ResponseEnum.ERROR,
                            Arrays.asList(new MyError(401,
                                    JwtAuthenticationErrorCode.BAD_CREDENTIALS,"bad credentials")),
                            null, null));
        }
    }

    private UserDetails provideUserDetailsFromLoginForm(String username, String password){
            Authentication authentication = authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return (UserDetails) authentication.getPrincipal();
    }

    // method that validate request form
    private List<MyError> validateAuthenticationRequest(JwtTokenRequest jwtTokenRequest){
        List<MyError> errors = new ArrayList<>();
        errors.addAll(validateUserName(jwtTokenRequest.getName()));
        errors.addAll(validateUserPassword(jwtTokenRequest.getPassword()));
        return errors;
    }

    // methods that validate each property
    private List<MyError> validateUserName(String name){
        List<MyError> errors = new ArrayList<>();
        if(isNull(name)){
            errors.add(new MyError(400, JwtAuthenticationErrorCode.NAME_NULL,
                    "parameter name is null"));
        } else if(isEmpty(name)){
            errors.add(new MyError(422, JwtAuthenticationErrorCode.NAME_MISSING,
                    "parameter name is not present"));
        }
        return errors;
    }

    private List<MyError> validateUserPassword(String password){
        List<MyError> errors = new ArrayList<>();
        if(isNull(password)){
            errors.add(new MyError(400, JwtAuthenticationErrorCode.PASSWORD_NULL,
                    "parameter password is null"));
        } else if(isEmpty(password)){
                errors.add(new MyError(422, JwtAuthenticationErrorCode.PASSWORD_MISSING,
                        "parameter password is not present"));
        }
        return errors;
    }

    // methods that validate single elements of properties
    private boolean isEmpty(String property){
        return property.equals("");
    }

    private boolean isNull(String property){
        return property == null;
    }


}
