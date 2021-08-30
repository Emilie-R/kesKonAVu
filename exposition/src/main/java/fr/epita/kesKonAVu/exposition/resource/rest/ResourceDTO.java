package fr.epita.kesKonAVu.exposition.resource.rest;

import fr.epita.kesKonAVu.domain.resource.CatalogReferenceEnum;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;

public class ResourceDTO {
    private Long idResource;
    private String title;
    private String year;
    private String pictureUrl;
    private String synopsis;
    private String actors;
    private String category;
    private String director;
    private Integer duration;
    private String externalKey;
    private CatalogReferenceEnum externalCatalogName;
    private ResourceTypeEnum resourceType;

    public ResourceDTO (){
    }

    public ResourceDTO (String title) {
        this.title = title;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getExternalKey ( ) {
        return externalKey;
    }

    public void setExternalKey (String externalKey) {
        this.externalKey = externalKey;
    }

    public Long getIdResource ( ) {
        return idResource;
    }

    public void setIdResource (Long idResource) {
        this.idResource = idResource;
    }

    public CatalogReferenceEnum getExternalCatalogName ( ) {
        return externalCatalogName;
    }

    public void setExternalCatalogName (CatalogReferenceEnum externalCatalogName) {
        this.externalCatalogName = externalCatalogName;
    }

    public ResourceTypeEnum getResourceType ( ) {
        return resourceType;
    }

    public void setResourceType (ResourceTypeEnum resourceType) {
        this.resourceType = resourceType;
    }
}
