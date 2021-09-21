package fr.epita.kesKonAVu.application.resource;

import fr.epita.kesKonAVu.domain.resource.Movie;
import fr.epita.kesKonAVu.domain.resource.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    private static final Logger LOG = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Autowired
    MovieRepository movieRepository;

    @Override
    public Movie findByTitle(String title) {
        LOG.info("findByTitle input : idResource =  " + title);
        return movieRepository.findByTitle(title);
    }

    @Override
    public Movie findByIdResource(Long idResource) {
        LOG.info("findByIdResource input : idResource =  " + idResource);
        return movieRepository.findByIdResource(idResource);
    }

    @Override
    public Movie findByImdbId(String externalKey) {
            LOG.info("findByImdbId input : externalKey =  " + externalKey);
        return movieRepository.findByImdbId(externalKey);
    }

}
