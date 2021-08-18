package fr.epita.kesKonAVu.exposition.member.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
public class MemberDTOLight {

    @JsonProperty("pseudo")
    @NotBlank
    private String pseudo;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    @NotBlank
    private String password;

    public MemberDTOLight() {
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
