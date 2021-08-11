package fr.epita.kesKonAVu.domain.user;

import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="utilisateur")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMember;

    private String pseudo;
    private String email;
    private String password;
    private LocalDate creationDate;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name="utilisateurSuivi")
    private Set<ResourceFollowUp> resourceFollowUps;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @JoinTable(name="utilisateurRole")
    private Set<UserRoleEnum> roles;

    //Specific methods
    public void AddMovieFollowUp(ResourceFollowUp resourceFollowUp){
        resourceFollowUps.add(resourceFollowUp);
    }
    public void RemoveMovieFollowUp(ResourceFollowUp resourceFollowUp){
        resourceFollowUps.remove(resourceFollowUp);
    }

    //constructors
    public Member (){
    }

    //getters et setters


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

    public Set<UserRoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRoleEnum> roles) {
        this.roles = roles;
    }
}
