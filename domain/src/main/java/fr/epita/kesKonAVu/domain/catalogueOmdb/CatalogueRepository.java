package fr.epita.kesKonAVu.domain.catalogueOmdb;

public interface CatalogueRepository {

    ItemCatalogue findItemByImdbId(final String ImdbId);

    SerieSeasonCatalogue findAllEpisodesOfSeason(final String imdbId, final int seasonNumber);
}
