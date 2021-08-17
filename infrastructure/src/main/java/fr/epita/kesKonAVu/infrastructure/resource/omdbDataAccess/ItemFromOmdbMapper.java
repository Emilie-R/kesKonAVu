package fr.epita.kesKonAVu.infrastructure.resource.omdbDataAccess;


import fr.epita.kesKonAVu.domain.resource.CatalogReferenceEnum;
import fr.epita.kesKonAVu.domain.resource.ExternalKey;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import org.springframework.stereotype.Component;

@Component
public class ItemFromOmdbMapper {

    public Resource resourceOmdbToResource(ItemFromOmdb itemFromOmdb){

        Resource resource = new Resource();
        resource.setTitle(itemFromOmdb.getTitle());
        resource.setYear(itemFromOmdb.getYear());
        resource.setPictureUrl(itemFromOmdb.getPoster());
        resource.setSynopsis(itemFromOmdb.getPlot());
        resource.setActors(itemFromOmdb.getActors());
        resource.setCategory(itemFromOmdb.getGenre());
        resource.setDirector(itemFromOmdb.getDirector());

        switch (itemFromOmdb.getType()) {
            case "movie" :
                resource.setResourceType(ResourceTypeEnum.MOVIE);
                break;
            case "series" :
                resource.setResourceType(ResourceTypeEnum.SERIE);
        }

        String imdbKey = "A RENSEIGNER";
//        imdbKey.setCatalogName(CatalogReferenceEnum.OMDBAPI);
//        imdbKey.setResourceId(itemFromOmdb.getImdbId());
        resource.setExternalKey(imdbKey);

        return resource;
    }
}
