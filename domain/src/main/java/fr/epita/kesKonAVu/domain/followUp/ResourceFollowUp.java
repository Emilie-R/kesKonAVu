package fr.epita.kesKonAVu.domain.followUp;

import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.user.Member;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="suiviResource")
public class ResourceFollowUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFollowUp;

    @ManyToOne
    private Resource resource;

    private StatusEnum status;
    private LocalDate creationDate;
    private LocalDate lastModificationDate;
    private int note;

    @ManyToOne
    private Member member;

    public ResourceFollowUp (){

    }

    public Long getIdFollowUp() {
        return idFollowUp;
    }

    public void setIdFollowUp(Long idFollowUp) {
        this.idFollowUp = idFollowUp;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(LocalDate lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Member getMember ( ) {
        return member;
    }

    public void setMember (Member member) {
        this.member = member;
    }
}
