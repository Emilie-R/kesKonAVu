package fr.epita.kesKonAVu.exposition.member.rest;

import fr.epita.kesKonAVu.domain.user.TypeRoleEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class MemberDTO {

    private String idMember;
    private String pseudo;
    private String email;
    private LocalDate creationDate;
    private LocalDateTime lastConnexionDateTime;
    private Set<TypeRoleEnum> roles;

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
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

    public LocalDateTime getLastConnexionDateTime() {
        return lastConnexionDateTime;
    }

    public void setLastConnexionDateTime(LocalDateTime lastConnexionDateTime) {
        this.lastConnexionDateTime = lastConnexionDateTime;
    }

    public Set<TypeRoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(Set<TypeRoleEnum> roles) {
        this.roles = roles;
    }
}
