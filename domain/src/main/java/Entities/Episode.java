package Entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Episode {
    private int id;
    private int number;
    private String title;
    private String externalId;
    private int seasonNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalogId",referencedColumnName="externalCatalogId")
    private ExternalCatalog externalCatalog;

    public Episode(){

    }

    public int getId ( ) {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public int getNumber ( ) {
        return number;
    }

    public void setNumber (int number) {
        this.number = number;
    }

    public String getTitle ( ) {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getExternalId ( ) {
        return externalId;
    }

    public void setExternalId (String externalId) {
        this.externalId = externalId;
    }

    public int getSeasonNumber ( ) {
        return seasonNumber;
    }

    public void setSeasonNumber (int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public ExternalCatalog getExternalCatalog ( ) {
        return externalCatalog;
    }

    public void setExternalCatalog (ExternalCatalog externalCatalog) {
        this.externalCatalog = externalCatalog;
    }
}
