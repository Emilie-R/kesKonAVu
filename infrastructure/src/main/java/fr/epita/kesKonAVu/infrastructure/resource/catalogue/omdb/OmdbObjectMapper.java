package fr.epita.kesKonAVu.infrastructure.resource.catalogue.omdb;


import fr.epita.kesKonAVu.domain.resource.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
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
        resource.setExternalCatalogName(CatalogReferenceEnum.OMDBAPI);
        resource.setExternalKey(itemFromOmdb.getImdbId());

        switch (itemFromOmdb.getType()) {
            case "movie" :
                resource.setResourceType(ResourceTypeEnum.MOVIE);
                break;
            case "series" :
                resource.setResourceType(ResourceTypeEnum.SERIE);
        }



        return resource;
    }

    public Resource itemOmdbToMovie(final ItemFromOmdb itemFromOmdb){

        Resource movie = new Resource();
        movie.setTitle(itemFromOmdb.getTitle());
        movie.setYear(itemFromOmdb.getYear());
        movie.setPictureUrl(itemFromOmdb.getPoster());
        movie.setSynopsis(itemFromOmdb.getPlot());
        movie.setActors(itemFromOmdb.getActors());
        movie.setCategory(itemFromOmdb.getGenre());
        movie.setDirector(itemFromOmdb.getDirector());
        movie.setResourceType(ResourceTypeEnum.MOVIE);

        movie.setExternalCatalogName(CatalogReferenceEnum.OMDBAPI);
        movie.setExternalKey(itemFromOmdb.getImdbId());

        return movie;
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

        serie.setExternalCatalogName(CatalogReferenceEnum.OMDBAPI);
        serie.setExternalKey(itemFromOmdb.getImdbId());

        return serie;
    }

    public Episode episodeLightOmdbToEpisode (EpisodeLightFromOMDB episodeLightFromOMDB) {
       Episode episode = new Episode();
       episode.setExternalCatalogName(CatalogReferenceEnum.OMDBAPI);
       episode.setExternalKey(episodeLightFromOMDB.getImdbId());
       episode.setTitle(episodeLightFromOMDB.getTitle());
       episode.setNumber(Integer.parseInt(episodeLightFromOMDB.getEpisodeNumber()));
       return episode;
    }

    public Set<Episode> listOfEpisodeOmdbToSetOfEpisodes (ListOfEpisodeFromOmdb listOfEpisodeFromOmdb) {
        Set<Episode> episodes = new HashSet<>();
        for (EpisodeLightFromOMDB episodeLightFromOMDB : listOfEpisodeFromOmdb.getEpisodes()) {
            Episode episode = episodeLightOmdbToEpisode(episodeLightFromOMDB);
            episode.setSeasonNumber(Integer.parseInt(listOfEpisodeFromOmdb.getSeason()));
            episodes.add(episode);
        }
        return episodes;
    }
}
