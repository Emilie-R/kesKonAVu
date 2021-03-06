package fr.epita.kesKonAVu.application.resource;

import fr.epita.kesKonAVu.domain.resource.Serie;
import fr.epita.kesKonAVu.domain.resource.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SerieServiceImpl implements SerieService{
    @Autowired
    SerieRepository serieRepository;

    @Override
    public Serie findByTitle (String title) {
        return serieRepository.findByTitle(title);
    }

    @Override
    public Serie findByIdResource (Long idResource) {
        return serieRepository.findByIdResource(idResource);
    }

    @Override
    public Serie findByImdbId(String externalKey) {
        return serieRepository.findByImdbId(externalKey);
    }
}
