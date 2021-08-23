package fr.epita.kesKonAVu.config.security.jwt;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String jwttoken;

    public JwtResponse() {
    }

    public JwtResponse(final String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return jwttoken;
    }

    public void setJwttoken(String jwttoken) {
        this.jwttoken = jwttoken;
    }
}
