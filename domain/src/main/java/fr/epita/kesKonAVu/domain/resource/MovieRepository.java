package fr.epita.kesKonAVu.domain.resource;

public interface MovieRepository {

    Movie findByTitle(String title);

    Movie findByIdResource (Long idResource);

    Movie findByImdbId(String externalKey);

    Movie save(Movie movie);
}
