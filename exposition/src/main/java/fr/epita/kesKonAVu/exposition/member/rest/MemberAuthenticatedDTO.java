package fr.epita.kesKonAVu.exposition.member.rest;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.epita.kesKonAVu.config.security.jwt.JwtResponse;
import fr.epita.kesKonAVu.domain.user.TypeRoleEnum;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public class MemberAuthenticatedDTO implements Serializable {

    private String pseudo;
    private String email;
    private LocalDate creationDate;
    private Set<TypeRoleEnum> roles;

    private JwtResponse jwtToken;

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

    public Set<TypeRoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(Set<TypeRoleEnum> roles) {
        this.roles = roles;
    }

    public JwtResponse getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(JwtResponse jwtToken) {
        this.jwtToken = jwtToken;
    }
}
