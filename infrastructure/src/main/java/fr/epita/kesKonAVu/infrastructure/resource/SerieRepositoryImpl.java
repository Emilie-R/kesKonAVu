package fr.epita.kesKonAVu.infrastructure.resource;

import fr.epita.kesKonAVu.domain.catalogueOmdb.CatalogueService;
import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.resource.Serie;
import fr.epita.kesKonAVu.domain.resource.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SerieRepositoryImpl implements SerieRepository {

    @Autowired
    SerieJpaRepository serieJpaRepository;

    @Autowired
    CatalogueService catalogueService;

    @Override
    public Serie findByTitle (String title) {
        Serie serieOut;
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

        Serie serieOut;
        Serie result = serieJpaRepository.findByIdResource(idResource);
        if (result != null){
            serieOut = result;
        } else {
            throw new NotFoundException("Serie n° " + idResource + " Not found");
        }

        return serieOut;
    }

    @Override
    public Serie findByImdbId(String externalKey) {
        Serie serieOut;
        Serie result = serieJpaRepository.findByImdbId(externalKey);
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
    public Serie findByIdWithAllEpisodes (Long idSerie) {
        return serieJpaRepository.findByIdWithAllEpisodes(idSerie);
    }

}
