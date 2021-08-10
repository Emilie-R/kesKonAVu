package Entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class EpisodeFollowUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Boolean status;
    private LocalDate creationDate;
    private LocalDate lastModificationDate;
    private int note;
    private Episode episode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serieFollowUp_id")
    private SerieFollowUp serieFollowUp;

    public EpisodeFollowUp(){
    }

    public int getId ( ) {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public Boolean getStatus ( ) {
        return status;
    }

    public void setStatus (Boolean status) {
        this.status = status;
    }

    public LocalDate getCreationDate ( ) {
        return creationDate;
    }

    public void setCreationDate (LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getLastModificationDate ( ) {
        return lastModificationDate;
    }

    public void setLastModificationDate (LocalDate lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public int getNote ( ) {
        return note;
    }

    public void setNote (int note) {
        this.note = note;
    }

    public Episode getEpisode ( ) {
        return episode;
    }

    public void setEpisode (Episode episode) {
        this.episode = episode;
    }
}
