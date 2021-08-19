package fr.epita.kesKonAVu.domain.user;

import fr.epita.kesKonAVu.domain.followUp.FollowUp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
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

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "member")
    private Set<FollowUp> followUps;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @JoinTable(name="utilisateurRole")
    private Set<TypeRoleEnum> roles = new HashSet<>();

    //constructors
    public Member (){
    }

    public Member(String pseudo, String email, String password) {
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
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

    public Set<FollowUp> getFollowUps () {
        return followUps;
    }

    public void setFollowUps (Set<FollowUp> followUps) {
        this.followUps = followUps;
    }

    public Set<TypeRoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(Set<TypeRoleEnum> roles) {
        this.roles = roles;
    }


    //Specific methods

    public void addResourceFollowUp(FollowUp resourceFollowUp) {
        this.followUps.add(resourceFollowUp);
    }

    public void removeResourceFollowUp(FollowUp resourceFollowUp) {
        this.followUps.remove(resourceFollowUp);
    }

    public void addRole (final TypeRoleEnum role) {
        this.roles.add(role);
    }

    public void removeRole (final TypeRoleEnum role) {
        this.roles.remove(role);
    }
}
