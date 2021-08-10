package fr.epita.kesKonAVu.domain.Entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMember;

    private String pseudo;
    private  String email;
    private String passWord;
    private String role;
    private LocalDate creationDate;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<ResourceFollowUp> resourceFollowUps;

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

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
}
