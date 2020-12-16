package mateusz.michal.chat.Model;

public class JwtTokenResponse {
    JwtAuthenticationErrorCode errorCode;
    String token;

    public JwtTokenResponse(JwtAuthenticationErrorCode errorCode, String token) {
        this.errorCode = errorCode;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public JwtAuthenticationErrorCode getErrorCode(){
        return errorCode;
    }
}
