package fr.epita.kesKonAVu.domain.resource;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Serie extends Resource {

    private String year;
    private String synopsis;
    private String actors;
    private String category;
    private String director;
    private String duration;

    @Enumerated(EnumType.STRING)
    @Column(name = "datasource")
    private CatalogReferenceEnum resourceDataSource;

    private Integer numberOfSeasons;
    private int numberOfEpisodes;

    @OneToMany (cascade = CascadeType.ALL)
    @JoinTable(name = "SerieEpisode")
    private Set<Episode> episodes;

    // constructeurs
    public Serie(){
    }

    // getters et setters

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public CatalogReferenceEnum getResourceDataSource() {
        return resourceDataSource;
    }

    public void setResourceDataSource(CatalogReferenceEnum resourceDataSource) {
        this.resourceDataSource = resourceDataSource;
    }

    public Integer getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(Integer numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public Set<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Set<Episode> episodes) {
        this.episodes = episodes;
    }
}
