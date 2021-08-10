package fr.epita.kesKonAVu.domain.Entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class ResourceFollowUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFollowUp;

    @ManyToOne
    private Resource resource;

    private String status;
    private LocalDate creationDate;
    private LocalDate lastModificationDate;
    private int note;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
