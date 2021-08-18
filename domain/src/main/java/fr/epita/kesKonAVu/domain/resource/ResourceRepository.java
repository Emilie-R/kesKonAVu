package fr.epita.kesKonAVu.domain.resource;

public interface ResourceRepository {

    Resource findMovieByTitle(String title);

    Resource findMovieByIdResource (Long idResource);

    Resource findMovieByExternalKey(String externalKey);

    Resource save(Resource resource);

    Resource getResourceFromCatalogueByImdbId(String externalKey);

    Resource getMovieFromCatalogueByImdbId(String externalKey);

    Resource findById(Long idResource);

}
