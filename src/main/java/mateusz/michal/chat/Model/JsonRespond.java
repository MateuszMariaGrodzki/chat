package mateusz.michal.chat.Model;

public class JsonRespond {
    private String error;

    public JsonRespond(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
