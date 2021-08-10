package Entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String year;
    private String pictureUrl;
    private String synopsis;
    private String actors;
    private String category;
    private String director;
    private LocalDate creationDate;
    private int duration;
    private String resourceType;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "catalogId",referencedColumnName="externalCatalogId")
    private ExternalKey externalKey;

    public Resource ( ) {
    }

    public Long getId ( ) {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getTitle ( ) {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getYear ( ) {
        return year;
    }

    public void setYear (String year) {
        this.year = year;
    }

    public String getPictureUrl ( ) {
        return pictureUrl;
    }

    public void setPictureUrl (String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getSynopsis ( ) {
        return synopsis;
    }

    public void setSynopsis (String synopsis) {
        this.synopsis = synopsis;
    }

    public String getActors ( ) {
        return actors;
    }

    public void setActors (String actors) {
        this.actors = actors;
    }

    public String getCategory ( ) {
        return category;
    }

    public void setCategory (String category) {
        this.category = category;
    }

    public String getDirector ( ) {
        return director;
    }

    public void setDirector (String director) {
        this.director = director;
    }

    public LocalDate getCreationDate ( ) {
        return creationDate;
    }

    public void setCreationDate (LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public int getDuration ( ) {
        return duration;
    }

    public void setDuration (int duration) {
        this.duration = duration;
    }

    public String getResourceType ( ) {
        return resourceType;
    }

    public void setResourceType (String resourceType) {
        this.resourceType = resourceType;
    }

    public ExternalKey getExternalCatalogId ( ) {
        return externalKey;
    }

    public void setExternalCatalogId (ExternalKey externalCatalog) {
        this.externalKey = externalCatalog;
    }
}
