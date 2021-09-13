package fr.epita.kesKonAVu.domain.catalogueOmdb;


import fr.epita.kesKonAVu.domain.resource.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CatalogueResourceMapper {

    public Resource itemCatalogueToResource(final ItemCatalogue itemCatalogue){

        Resource resource = new Resource();
        resource.setResourceDataSource(CatalogReferenceEnum.OMDBAPI);

        resource.setTitle(itemCatalogue.getTitle());
        resource.setYear(itemCatalogue.getYear());
        resource.setPictureUrl(itemCatalogue.getPoster());
        resource.setSynopsis(itemCatalogue.getPlot());
        resource.setActors(itemCatalogue.getActors());
        resource.setCategory(itemCatalogue.getGenre());
        resource.setDirector(itemCatalogue.getDirector());
        resource.setImdbId(itemCatalogue.getImdbId());
        resource.setDuration(itemCatalogue.getRuntime());

        switch (itemCatalogue.getType()) {
            case "movie" :
                resource.setResourceType(ResourceTypeEnum.MOVIE);
                break;
            case "series" :
                resource.setResourceType(ResourceTypeEnum.SERIE);
        }



        return resource;
    }

    public Resource itemCatalogueToMovie(final ItemCatalogue itemCatalogue){

        Resource movie = new Resource();
        movie.setResourceDataSource(CatalogReferenceEnum.OMDBAPI);

        movie.setTitle(itemCatalogue.getTitle());
        movie.setYear(itemCatalogue.getYear());
        movie.setPictureUrl(itemCatalogue.getPoster());
        movie.setSynopsis(itemCatalogue.getPlot());
        movie.setActors(itemCatalogue.getActors());
        movie.setCategory(itemCatalogue.getGenre());
        movie.setDirector(itemCatalogue.getDirector());
        movie.setResourceType(ResourceTypeEnum.MOVIE);
        movie.setDuration(itemCatalogue.getRuntime());
        movie.setImdbId(itemCatalogue.getImdbId());

        return movie;
    }

    public Serie itemCatalogueToSerie(ItemCatalogue itemCatalogue){

        Serie serie = new Serie();
        serie.setResourceDataSource(CatalogReferenceEnum.OMDBAPI);

        serie.setTitle(itemCatalogue.getTitle());
        serie.setYear(itemCatalogue.getYear());
        serie.setPictureUrl(itemCatalogue.getPoster());
        serie.setSynopsis(itemCatalogue.getPlot());
        serie.setActors(itemCatalogue.getActors());
        serie.setCategory(itemCatalogue.getGenre());
        serie.setDirector(itemCatalogue.getDirector());
        serie.setResourceType(ResourceTypeEnum.SERIE);
        serie.setDuration(itemCatalogue.getRuntime());
        if (itemCatalogue.getTotalSeasons() != null ) {
            serie.setNumberOfSeasons(Integer.parseInt(itemCatalogue.getTotalSeasons()));
        }

        serie.setImdbId(itemCatalogue.getImdbId());

        return serie;
    }

    public Episode episodeCatalogueOmdbToEpisode(EpisodeCatalogue episodeCatalogue) {
       Episode episode = new Episode();
       episode.setEpisodeDataSource(CatalogReferenceEnum.OMDBAPI);

       episode.setImdbId(episodeCatalogue.getImdbId());
       episode.setTitle(episodeCatalogue.getTitle());
       if (episodeCatalogue.getEpisodeNumber() != null ) {
           episode.setNumber(Integer.parseInt(episodeCatalogue.getEpisodeNumber()));
       }
       return episode;
    }

    public Set<Episode> SerieSeasonToSetOfEpisodes(SerieSeasonCatalogue serieSeasonCatalogue) {
        Set<Episode> episodes = new HashSet<>();

        for (EpisodeCatalogue episodeLightFromOMDB : serieSeasonCatalogue.getEpisodes()) {
            Episode episode = episodeCatalogueOmdbToEpisode(episodeLightFromOMDB);
            episode.setSeasonNumber(Integer.parseInt(serieSeasonCatalogue.getSeason()));
            episodes.add(episode);
        }
        return episodes;
    }
}