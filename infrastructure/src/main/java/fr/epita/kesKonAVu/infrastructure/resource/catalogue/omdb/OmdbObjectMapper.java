package fr.epita.kesKonAVu.infrastructure.resource.catalogue.omdb;


import fr.epita.kesKonAVu.domain.resource.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class OmdbObjectMapper {

    public Resource itemOmdbToResource(final ItemFromOmdb itemFromOmdb){

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

        ExternalKey imdbKey = new ExternalKey();
        imdbKey.setCatalogName(CatalogReferenceEnum.OMDBAPI);
        imdbKey.setResourceId(itemFromOmdb.getImdbId());
        resource.setExternalKey(imdbKey);

        return resource;
    }

    public Resource itemOmdbToMovie(final ItemFromOmdb itemFromOmdb){

        Resource resource = new Resource();
        resource.setTitle(itemFromOmdb.getTitle());
        resource.setYear(itemFromOmdb.getYear());
        resource.setPictureUrl(itemFromOmdb.getPoster());
        resource.setSynopsis(itemFromOmdb.getPlot());
        resource.setActors(itemFromOmdb.getActors());
        resource.setCategory(itemFromOmdb.getGenre());
        resource.setDirector(itemFromOmdb.getDirector());
        resource.setResourceType(ResourceTypeEnum.MOVIE);

        ExternalKey imdbKey = new ExternalKey();
        imdbKey.setCatalogName(CatalogReferenceEnum.OMDBAPI);
        imdbKey.setResourceId(itemFromOmdb.getImdbId());
        resource.setExternalKey(imdbKey);

        return resource;
    }

    public Serie itemOmdbToSerie(ItemFromOmdb itemFromOmdb){
        Serie serie = new Serie();
        serie.setTitle(itemFromOmdb.getTitle());
        serie.setYear(itemFromOmdb.getYear());
        serie.setPictureUrl(itemFromOmdb.getPoster());
        serie.setSynopsis(itemFromOmdb.getPlot());
        serie.setActors(itemFromOmdb.getActors());
        serie.setCategory(itemFromOmdb.getGenre());
        serie.setDirector(itemFromOmdb.getDirector());
        serie.setResourceType(ResourceTypeEnum.SERIE);

        if (itemFromOmdb.getTotalSeasons() != null ) {
            serie.setNumberOfSeasons(Integer.parseInt(itemFromOmdb.getTotalSeasons()));
        }

        ExternalKey imdbKey = new ExternalKey();
        imdbKey.setCatalogName(CatalogReferenceEnum.OMDBAPI);
        imdbKey.setResourceId(itemFromOmdb.getImdbId());
        serie.setExternalKey(imdbKey);

        return serie;
    }

    public Episode episodeLightOmdbToEpisode (EpisodeLightFromOMDB episodeLightFromOMDB) {
       Episode episode = new Episode();
       return episode;
    }

    public Set<Episode> listOfEpisodeOmdbToSetOfEpisodes (ListOfEpisodeFromOmdb listOfEpisodeFromOmdb) {
        Set<Episode> episodes = new HashSet<>();
        return episodes;
    }
}
