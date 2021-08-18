package fr.epita.kesKonAVu.infrastructure.resource;

import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import fr.epita.kesKonAVu.domain.resource.Serie;
import fr.epita.kesKonAVu.domain.resource.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SerieRepositoryImpl implements SerieRepository {

    @Autowired
    SerieJpaRepository serieJpaRepository;

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
}
