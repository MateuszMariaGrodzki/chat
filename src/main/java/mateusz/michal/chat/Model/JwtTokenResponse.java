package mateusz.michal.chat.Model;

public class JwtTokenResponse {
    JwtAutenticationErrorCode errorCode;
    String token;

    public JwtTokenResponse(JwtAutenticationErrorCode errorCode, String token) {
        this.errorCode = errorCode;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public JwtAutenticationErrorCode getErrorCode(){
        return errorCode;
    }
}
