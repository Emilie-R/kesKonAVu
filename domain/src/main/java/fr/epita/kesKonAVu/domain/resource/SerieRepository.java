package fr.epita.kesKonAVu.domain.resource;

import org.springframework.data.repository.query.Param;

public interface SerieRepository {

    Serie findByTitle(String title);

    Serie findByIdResource(Long idResource);

    Serie findByExternalKey(String externalKey);

    Serie save(Serie serie);

    Serie findByIdWithAllEpisodes (@Param("id") Long idSerie);

}
