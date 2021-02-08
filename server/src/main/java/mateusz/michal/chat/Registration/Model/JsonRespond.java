package mateusz.michal.chat.Registration.Model;

public class JsonRespond {
    private RegisterFormErrorCode errorCode;
    private boolean isRegistered;

    public JsonRespond(RegisterFormErrorCode errorCode, boolean isRegistered) {
        this.errorCode = errorCode;
        this.isRegistered = isRegistered;
    }

    public RegisterFormErrorCode getErrorCode() {
        return errorCode;
    }

    public boolean isRegistered(){
        return isRegistered;
    }
}
