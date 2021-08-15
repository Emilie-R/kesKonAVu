package fr.epita.kesKonAVu.infrastructure.resource.omdbApi;

import fr.epita.kesKonAVu.domain.resource.CatalogReferenceEnum;
import fr.epita.kesKonAVu.domain.resource.ExternalKey;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import org.springframework.stereotype.Component;

@Component
public class ResourceOmdbMapper {

    public Resource ResourceOmdbToResource(ResourceOmdb resourceOmdb){

        Resource resource = new Resource();
        resource.setTitle(resourceOmdb.getTitle());
        resource.setYear(resourceOmdb.getYear());
        resource.setPictureUrl(resourceOmdb.getPoster());
        resource.setSynopsis(resourceOmdb.getPlot());
        resource.setActors(resourceOmdb.getActors());
        resource.setCategory(resourceOmdb.getGenre());
        resource.setDirector(resourceOmdb.getDirector());

        System.out.println(resourceOmdb.getType());
        switch (resourceOmdb.getType()) {
            case "movie" :
                resource.setResourceType(ResourceTypeEnum.MOVIE);
                break;
            case "serie" :
                resource.setResourceType(ResourceTypeEnum.SERIE);
        }

        ExternalKey omdbKey = new ExternalKey();
        omdbKey.setCatalogName(CatalogReferenceEnum.OMDBAPI);
        omdbKey.setResourceId(resourceOmdb.getImdbId());
        resource.setExternalKey(omdbKey);

        return resource;
    }
}
