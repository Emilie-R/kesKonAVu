package fr.epita.kesKonAVu.domain.catalogue;

import fr.epita.kesKonAVu.domain.resource.Episode;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.Serie;

import java.util.Set;

public interface CatalogueService {

    Resource findResourceByImdbId(final String imdbId);

    Resource findMovieByImdbId(final String imdbId);

    Serie findSerieByImdbId(final String imdbId);

    Set<Episode> findAllEpisodes(final Serie serie);
}
