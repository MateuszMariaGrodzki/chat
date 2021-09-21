package mateusz.michal.chat.Authorization.Model;

import mateusz.michal.chat.Structure.RespondStructure.IData;

public class JwtTokenResponse implements IData {
    private String token;
    private String name;
    private String email;

    public JwtTokenResponse(String token, String name, String email) {
        this.token = token;
        this.name = name;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public String getName() { return name; }

    public String getEmail() { return email; }
}
