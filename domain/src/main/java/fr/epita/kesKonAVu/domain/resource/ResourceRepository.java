package fr.epita.kesKonAVu.domain.resource;

public interface ResourceRepository {

    Resource findMovieByTitle(String title);

    Resource findMovieByIdResource (Long idResource);

    Resource findMovieByImdbId(String externalKey);

    Resource save(Resource resource);

    Resource findById(Long idResource);

}
