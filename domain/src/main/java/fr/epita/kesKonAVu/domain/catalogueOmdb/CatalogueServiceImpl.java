package fr.epita.kesKonAVu.domain.catalogueOmdb;

import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.resource.Episode;
import fr.epita.kesKonAVu.domain.resource.Movie;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.Serie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CatalogueServiceImpl implements CatalogueService {

    @Autowired
    CatalogueResourceMapper catalogueResourceMapper;

    @Autowired
    CatalogueRepository catalogueRepository;

    private static final Logger LOG = LoggerFactory.getLogger(CatalogueServiceImpl.class);

    @Override
    public Movie findMovieByImdbId(final String imdbId) {
        return catalogueResourceMapper.itemCatalogueToMovie(catalogueRepository.findItemByImdbId(imdbId));
    }

    @Override
    public Serie findSerieByImdbId(final String imdbId) {
        return catalogueResourceMapper.itemCatalogueToSerie(catalogueRepository.findItemByImdbId(imdbId));
    }

    @Override
    public Set<Episode> findAllEpisodes(final Serie serie) {
        Set<Episode> allEpisodes = new HashSet<>();

        if (serie.getNumberOfSeasons() == null) {
            LOG.warn("Serie sans Episode/ ni saison : Id / Titre / ImdbId = "
                    + serie.getIdResource() + " / " + serie.getTitle() + " / " + serie.getImdbId());
            return allEpisodes;
        }

        for (int season = 1; season <= serie.getNumberOfSeasons(); season++) {
            try {
                allEpisodes.addAll(catalogueResourceMapper.SerieSeasonToSetOfEpisodes(
                        catalogueRepository.findAllEpisodesOfSeason(serie.getImdbId(), season)));
            } catch (NotFoundException e) {
                /* No Episodes for the season : Case can happen with the oldest series */
            }
        }
        return allEpisodes;
    }
}

