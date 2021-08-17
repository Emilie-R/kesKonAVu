package fr.epita.kesKonAVu.domain.resource;

import java.util.Set;

public interface SerieRepository {

    Serie findByTitle(String title);

    Serie findByIdResource(Long idResource);

    Serie findByExternalKey(String externalKey);

    Serie save(Serie serie);

    Serie getSerieFromCatalogueByImdbId(String externalKey);

    Set<Episode> getAllEpisodesFromCatalogue(Serie serie);
}
