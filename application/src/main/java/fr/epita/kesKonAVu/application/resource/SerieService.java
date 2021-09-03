package fr.epita.kesKonAVu.application.resource;

import fr.epita.kesKonAVu.domain.resource.Serie;

public interface SerieService {
    Serie findByTitle(String title);

    Serie findByIdResource(Long idResource);

    Serie findByImdbId(String imdbId);
}
