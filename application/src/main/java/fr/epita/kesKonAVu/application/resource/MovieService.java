package fr.epita.kesKonAVu.application.resource;

import fr.epita.kesKonAVu.domain.resource.Movie;
import fr.epita.kesKonAVu.domain.resource.Resource;

public interface MovieService {

    Movie findByTitle(String title);

    Movie findByIdResource(Long idResource);

    Movie findByImdbId(String imdbId);

}
