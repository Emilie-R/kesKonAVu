package fr.epita.kesKonAVu.domain.resource;

public interface ResourceRepository {

    Resource findByTitle(String title);

    Resource findByIdResource(String idResource);

    Resource findByExternalKey(String externalKey);
}
