package fr.epita.kesKonAVu.domain.episodeFollowUp;

import fr.epita.kesKonAVu.domain.resource.Episode;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "suiviEpisode")
public class EpisodeFollowUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Episode episode;

    @Enumerated(EnumType.STRING)
    private EpisodeStatusEnum status;

    private LocalDate lastModificationDate;

    public EpisodeFollowUp(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    public LocalDate getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(LocalDate lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public EpisodeStatusEnum getStatus() {
        return status;
    }

    public void setStatus(EpisodeStatusEnum status) {
        this.status = status;
    }
}
