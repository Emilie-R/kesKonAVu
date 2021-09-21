package fr.epita.kesKonAVu.domain.resource;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="resource")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResource;

    private String title;
    private String pictureUrl;

    @Enumerated(EnumType.STRING)
    private ResourceTypeEnum resourceType;

    private String imdbId;
    private LocalDateTime lastModificationDateTime;

    public Resource() {
    }

    public Long getIdResource() {
        return idResource;
    }

    public void setIdResource(Long idResource) {
        this.idResource = idResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public LocalDateTime getLastModificationDateTime() {
        return lastModificationDateTime;
    }

    public void setLastModificationDateTime(LocalDateTime lastModificationDateTime) {
        this.lastModificationDateTime = lastModificationDateTime;
    }

    public ResourceTypeEnum getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceTypeEnum resourceType) {
        this.resourceType = resourceType;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    @Override
    public String toString() {
        StringBuilder resource = new StringBuilder();
        resource.append(" Resource : ");
        resource.append("+ idResource " + this.idResource + " ");
        resource.append("+ title " + this.getTitle() + " ");
        resource.append("+ External ID " + this.getImdbId());
        return resource.toString();
    }
}
