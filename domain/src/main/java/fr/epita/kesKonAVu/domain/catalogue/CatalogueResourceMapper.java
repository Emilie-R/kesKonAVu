package fr.epita.kesKonAVu.domain.catalogue;


import fr.epita.kesKonAVu.domain.resource.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CatalogueResourceMapper {

    public Resource itemCatalogueToResource(final ItemCatalogue itemCatalogue){

        Resource resource = new Resource();
        resource.setTitle(itemCatalogue.getTitle());
        resource.setYear(itemCatalogue.getYear());
        resource.setPictureUrl(itemCatalogue.getPoster());
        resource.setSynopsis(itemCatalogue.getPlot());
        resource.setActors(itemCatalogue.getActors());
        resource.setCategory(itemCatalogue.getGenre());
        resource.setDirector(itemCatalogue.getDirector());
        resource.setExternalCatalogName(CatalogReferenceEnum.OMDBAPI);
        resource.setExternalKey(itemCatalogue.getImdbId());

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
        movie.setTitle(itemCatalogue.getTitle());
        movie.setYear(itemCatalogue.getYear());
        movie.setPictureUrl(itemCatalogue.getPoster());
        movie.setSynopsis(itemCatalogue.getPlot());
        movie.setActors(itemCatalogue.getActors());
        movie.setCategory(itemCatalogue.getGenre());
        movie.setDirector(itemCatalogue.getDirector());
        movie.setResourceType(ResourceTypeEnum.MOVIE);

        movie.setExternalCatalogName(CatalogReferenceEnum.OMDBAPI);
        movie.setExternalKey(itemCatalogue.getImdbId());

        return movie;
    }

    public Serie itemCatalogueToSerie(ItemCatalogue itemCatalogue){
        Serie serie = new Serie();
        serie.setTitle(itemCatalogue.getTitle());
        serie.setYear(itemCatalogue.getYear());
        serie.setPictureUrl(itemCatalogue.getPoster());
        serie.setSynopsis(itemCatalogue.getPlot());
        serie.setActors(itemCatalogue.getActors());
        serie.setCategory(itemCatalogue.getGenre());
        serie.setDirector(itemCatalogue.getDirector());
        serie.setResourceType(ResourceTypeEnum.SERIE);

        if (itemCatalogue.getTotalSeasons() != null ) {
            serie.setNumberOfSeasons(Integer.parseInt(itemCatalogue.getTotalSeasons()));
        }

        serie.setExternalCatalogName(CatalogReferenceEnum.OMDBAPI);
        serie.setExternalKey(itemCatalogue.getImdbId());

        return serie;
    }

    public Episode episodeCatalogueOmdbToEpisode(EpisodeCatalogue episodeCatalogue) {
       Episode episode = new Episode();
       episode.setExternalCatalogName(CatalogReferenceEnum.OMDBAPI);
       episode.setExternalKey(episodeCatalogue.getImdbId());
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
