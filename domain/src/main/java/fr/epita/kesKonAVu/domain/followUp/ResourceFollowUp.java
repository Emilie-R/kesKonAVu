package fr.epita.kesKonAVu.domain.followUp;

import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeStatusEnum;
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

    @ManyToOne(fetch = FetchType.EAGER)
    private Resource resource;

    @ManyToOne (fetch = FetchType.LAZY)
    private Member member;

    @Enumerated(EnumType.STRING)
    private statusEnum status;

    private Integer note;

    private LocalDate creationDate;
    private LocalDate lastModificationDate;




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

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public Member getMember ( ) {
        return member;
    }

    public void setMember (Member member) {
        this.member = member;
    }

    public statusEnum getStatus() {
        return status;
    }

    public void setStatus(statusEnum status) {
        this.status = status;
    }
}
