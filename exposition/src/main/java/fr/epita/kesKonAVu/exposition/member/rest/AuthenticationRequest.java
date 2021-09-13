package fr.epita.kesKonAVu.exposition.member.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class AuthenticationRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("pseudo")
    @NotEmpty
    @NotBlank
    private String pseudo;

    @JsonProperty("password")
    @NotEmpty
    @NotBlank
    private String password;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String pseudo, String password) {
        this.pseudo = pseudo;
        this.password = password;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
