package fr.epita.kesKonAVu.exposition.member.rest;

import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;
import fr.epita.kesKonAVu.domain.user.TypeRoleEnum;

import java.time.LocalDate;
import java.util.Set;

public class MemberDTO {

    private Long idMember;
    private String pseudo;
    private String email;
    private String password;
    private LocalDate creationDate;
    private Set<ResourceFollowUp> resourceFollowUps;
    private Set<TypeRoleEnum> roles;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Set<ResourceFollowUp> getResourceFollowUps() {
        return resourceFollowUps;
    }

    public void setResourceFollowUps(Set<ResourceFollowUp> resourceFollowUps) {
        this.resourceFollowUps = resourceFollowUps;
    }

    public Set<TypeRoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(Set<TypeRoleEnum> roles) {
        this.roles = roles;
    }
}
