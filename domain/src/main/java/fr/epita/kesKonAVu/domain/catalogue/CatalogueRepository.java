package fr.epita.kesKonAVu.domain.catalogue;

public interface CatalogueRepository {

    ItemCatalogue findItemByImdbId(final String ImdbId);

    SerieSeasonCatalogue findAllEpisodesOfSeason(final String imdbId, final int seasonNumber);
}
