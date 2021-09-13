package fr.epita.kesKonAVu.exposition.member.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Validated
public class MemberDTOLight {

    @JsonProperty("pseudo")
    @NotBlank
    @NotEmpty
    private String pseudo;

    @JsonProperty("email")
    @Email
    private String email;

    @JsonProperty("password")
    @NotBlank
    @NotEmpty
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
