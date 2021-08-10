package Entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class ResourceFollowUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String status;
    private LocalDate creationDate;
    private LocalDate lastModificationDate;
    private int note;
    private Resource resource;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    public ResourceFollowUp (){

    }

    public int getId ( ) {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public int getNote ( ) {
        return note;
    }

    public void setNote (int note) {
        this.note = note;
    }

    public LocalDate getLastModificationDate ( ) {
        return lastModificationDate;
    }

    public void setLastModificationDate (LocalDate lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }
    public Member getUser ( ) {
        return member;
    }

    public void setUser (Member member) {
        this.member = member;
    }

    public String getStatus ( ) {
        return status;
    }

    public void setStatus (String status) {
        this.status = status;
    }

    public LocalDate getCreationDate ( ) {
        return creationDate;
    }

    public void setCreationDate (LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Resource getMovie ( ) {
        return resource;
    }

    public void setMovie (Resource resource) {
        this.resource = resource;
    }
}
