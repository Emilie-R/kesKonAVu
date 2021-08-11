package fr.epita.kesKonAVu.domain.resource;

public interface SerieRepository {

    Serie findByTitle(String title);

    Serie findByIdResource(String idResource);

    Serie findByExternalKey(String externalKey);
}
