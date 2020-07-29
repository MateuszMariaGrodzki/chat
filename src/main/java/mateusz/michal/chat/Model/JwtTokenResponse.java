package mateusz.michal.chat.Model;

public class JwtTokenResponse {

    String errorCode;
    String token;

    public JwtTokenResponse(String errorCode, String token) {
        this.errorCode = errorCode;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getErrorCode(){
        return errorCode;
    }
}
