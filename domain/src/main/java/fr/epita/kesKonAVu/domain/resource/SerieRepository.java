package fr.epita.kesKonAVu.domain.resource;

public interface SerieRepository {

    Serie findByTitle(String title);

    Serie findByIdResource(Long idResource);

    Serie findByExternalKey(String externalKey);
}
