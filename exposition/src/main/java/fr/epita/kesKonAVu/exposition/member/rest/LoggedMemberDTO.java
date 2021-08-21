package fr.epita.kesKonAVu.exposition.member.rest;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.epita.kesKonAVu.config.security.jwt.JwtResponse;

import java.io.Serializable;
import java.time.LocalDate;

public class LoggedMemberDTO implements Serializable {


    private Long idMember;
    private String pseudo;
    private String email;
    private LocalDate creationDate;

    private JwtResponse jwtToken;

    public Long getIdMember() {
        return idMember;
    }

    public void setIdMember(Long idMember) {
        this.idMember = idMember;
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public JwtResponse getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(JwtResponse jwtToken) {
        this.jwtToken = jwtToken;
    }
}
