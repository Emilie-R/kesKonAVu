package fr.epita.kesKonAVu.domain.followUp;

import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeFollowUp;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.user.Member;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="suiviResource")
public class FollowUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFollowUp;

    @ManyToOne(fetch = FetchType.EAGER)
    private Resource resource;

    @ManyToOne (fetch = FetchType.LAZY)
    private Member member;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    private Integer note;

    private LocalDateTime creationDateTime;
    private LocalDateTime lastModificationDateTime;

    private Float progression;
    private Integer numberOfUnseenEpisodes;

    @OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinTable(name="suiviResourceSuiviEpisode")
    private Set<EpisodeFollowUp> episodeFollowUps;


    public FollowUp (){

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

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public LocalDateTime getLastModificationDateTime() {
        return lastModificationDateTime;
    }

    public void setLastModificationDateTime(LocalDateTime lastModificationDateTime) {
        this.lastModificationDateTime = lastModificationDateTime;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
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

    public Float getProgression ( ) {
        return progression;
    }

    public void setProgression (Float progression) {
        this.progression = progression;
    }

    public Integer getNumberOfUnseenEpisodes ( ) {
        return numberOfUnseenEpisodes;
    }

    public void setNumberOfUnseenEpisodes (Integer numberOfUnseenEpisodes) {
        this.numberOfUnseenEpisodes = numberOfUnseenEpisodes;
    }

    public Set<EpisodeFollowUp> getEpisodeFollowUps ( ) {
        return episodeFollowUps;
    }

    public void setEpisodeFollowUps (Set<EpisodeFollowUp> episodeFollowUps) {
        this.episodeFollowUps = episodeFollowUps;
    }

    public void addEpisodeFollowup (EpisodeFollowUp episodeFollowUp) {
        this.episodeFollowUps.add(episodeFollowUp);
    }
}
