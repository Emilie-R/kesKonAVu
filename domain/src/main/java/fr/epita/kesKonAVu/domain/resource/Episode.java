package fr.epita.kesKonAVu.domain.resource;

import javax.persistence.*;

@Entity
@Table(name="catalogueEpisode")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEpisode;

    private int seasonNumber;
    private int number;

    private String title;

    @OneToOne(fetch = FetchType.EAGER)
    private ExternalKey externalKey;

    public Episode(){

    }

    public Long getIdEpisode() {
        return idEpisode;
    }

    public void setIdEpisode(Long idEpisode) {
        this.idEpisode = idEpisode;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ExternalKey getExternalKey() {
        return externalKey;
    }

    public void setExternalKey(ExternalKey externalKey) {
        this.externalKey = externalKey;
    }
}
