package mateusz.michal.chat.Model;

public class JwtTokenResponse {
    JwtAuthenticationErrorCode errorCode;
    String token;
    String name;
    String email;

    public JwtTokenResponse(JwtAuthenticationErrorCode errorCode, String token, String name, String email) {
        this.errorCode = errorCode;
        this.token = token;
        this.name = name;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public JwtAuthenticationErrorCode getErrorCode(){
        return errorCode;
    }
}
