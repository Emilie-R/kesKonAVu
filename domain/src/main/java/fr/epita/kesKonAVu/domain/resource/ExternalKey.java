package fr.epita.kesKonAVu.domain.resource;


import javax.persistence.*;

@Entity
@Table(name="catalogueReferenceExterne")
public class ExternalKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private CatalogReferenceEnum catalogName;
    private String resourceId;

    public ExternalKey(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CatalogReferenceEnum getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(CatalogReferenceEnum catalogName) {
        this.catalogName = catalogName;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
