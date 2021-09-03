package fr.epita.kesKonAVu.application.resource;

import fr.epita.kesKonAVu.domain.resource.Resource;

public interface ResourceService {
    Resource findMovieByTitle(String title);

    Resource findMovieByIdResource(Long idResource);

    Resource findMovieByImdbId(String imdbId);

}
