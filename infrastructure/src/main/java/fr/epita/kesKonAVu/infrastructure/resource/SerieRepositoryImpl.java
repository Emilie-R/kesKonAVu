package fr.epita.kesKonAVu.infrastructure.resource;

import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.resource.Episode;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import fr.epita.kesKonAVu.domain.resource.Serie;
import fr.epita.kesKonAVu.domain.resource.SerieRepository;
import fr.epita.kesKonAVu.infrastructure.resource.catalogue.CatalogueApiAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SerieRepositoryImpl implements SerieRepository {

    @Autowired
    SerieJpaRepository serieJpaRepository;

    @Autowired
    CatalogueApiAccess catalogueApiAccess;

    @Override
    public Serie findByTitle (String title) {
        Serie serieOut = new Serie();
        Serie result = serieJpaRepository.findByTitle(title);
        if (result != null){
            serieOut = result;
        } else {
            throw new NotFoundException("Serie dont le titre est \"" + title + "\" Not found");
        }

        return serieOut;
    }

    @Override
    public Serie findByIdResource (Long idResource) {

        Serie serieOut = new Serie();
        Serie result = serieJpaRepository.findByIdResource(idResource);
        if (result != null){
            serieOut = result;
        } else {
            throw new NotFoundException("Serie n° " + idResource + " Not found");
        }

        return serieOut;
    }

    @Override
    public Serie findByExternalKey (String externalKey) {
        Serie serieOut = new Serie();
        Serie result = serieJpaRepository.findByExternalKey(externalKey);
        if (result != null){
            serieOut = result;
        } else {
            throw new NotFoundException("Serie n° " + externalKey + " Not found");
        }

        return serieOut;
    }

    @Override
    public Serie save(Serie serie) {
        return serieJpaRepository.save(serie);
    }

    @Override
    public Serie getSerieFromCatalogueByImdbId(String externalKey) {
        return catalogueApiAccess.findSerieByImdbId(externalKey);
    }

    @Override
    public Set<Episode> getAllEpisodesFromCatalogue(Serie serie) {
        return catalogueApiAccess.findAllEpisodes(serie);
    }
}
