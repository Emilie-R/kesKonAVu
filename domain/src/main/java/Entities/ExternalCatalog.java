package Entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ExternalCatalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String externalCatalogId;

    public ExternalCatalog (){
    }

    public int getId ( ) {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getName ( ) {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getExternalCatalogId ( ) {
        return externalCatalogId;
    }

    public void setExternalCatalogId (String externalCatalogId) {
        this.externalCatalogId = externalCatalogId;
    }
}
