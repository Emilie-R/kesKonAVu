package fr.epita.kesKonAVu.infrastructure.resource.catalogue;

import fr.epita.kesKonAVu.domain.resource.Episode;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.Serie;

import java.util.Set;

public interface CatalogueApiAccess {

    public Resource findResourceByImdbId(String imdbId);

    public Resource findMovieByImdbId(String imdbId);

    public Serie findSerieByImdbId(String imdbId);

    public Set<Episode> findAllEpisodes(Serie serie);
}
