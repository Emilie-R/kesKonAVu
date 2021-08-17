package fr.epita.kesKonAVu.domain.resource;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="catalogueResource")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResource;

    private String title;
    private String year;
    private String pictureUrl;
    private String synopsis;
    private String actors;
    private String category;
    private String director;
    private LocalDate creationDate;
    private Integer duration;

    @Enumerated(EnumType.STRING)
    private ResourceTypeEnum resourceType;

    private String externalKey;

    private String externalCatalogName;


    public Resource ( ) {
    }

    public Long getIdResource( ) {
        return idResource;
    }

    public void setIdResource(Long idResource) {
        this.idResource = idResource;
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

    public Integer getDuration ( ) {
        return duration;
    }

    public void setDuration (Integer duration) {
        this.duration = duration;
    }

    public ResourceTypeEnum getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceTypeEnum resourceType) {
        this.resourceType = resourceType;
    }

    public String getExternalKey ( ) {
        return externalKey;
    }

    public void setExternalKey (String externalKey) {
        this.externalKey = externalKey;
    }

    public String getExternalCatalogName ( ) {
        return externalCatalogName;
    }

    public void setExternalCatalogName (String externalCatalogName) {
        this.externalCatalogName = externalCatalogName;
    }

    @Override
    public String toString() {
        StringBuilder resource = new StringBuilder();
        resource.append(" Resource : ");
        resource.append("+ idResource " + this.idResource + " ");
        resource.append("+ title " + this.getTitle() + " ");
        resource.append("+ External ID " + this.getExternalKey());
        resource.append("+ from catalog " + this.getExternalCatalogName());
        return resource.toString();
    }
}
