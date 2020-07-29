package mateusz.michal.chat.Model;

public class JsonRespond {
    private String errorCode;
    private boolean isRegistered;

    public JsonRespond(String errorCode, boolean isRegistered) {
        this.errorCode = errorCode;
        this.isRegistered = isRegistered;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public boolean isRegistered(){
        return isRegistered;
    }
}
