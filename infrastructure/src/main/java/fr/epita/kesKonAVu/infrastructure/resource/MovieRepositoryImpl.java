package fr.epita.kesKonAVu.infrastructure.resource;

import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.resource.Movie;
import fr.epita.kesKonAVu.domain.resource.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieRepositoryImpl implements MovieRepository {

    @Autowired
    MovieJpaRepository movieJpaRepository;

    @Override
    public Movie findByTitle(String title) {
        if (movieJpaRepository.findByTitle(title) == null)
        {
            throw new NotFoundException("Movie dont le titre est " + title + " Not found");
        }
        return movieJpaRepository.findByTitle(title);
    }

    @Override
    public Movie findByIdResource(Long idResource) {
        return movieJpaRepository.findById(idResource).orElseThrow(
                () -> new NotFoundException("Movie n° " + idResource + " Not found"));
    }

    @Override
    public Movie findByImdbId(String externalKey) {
        if (movieJpaRepository.findByImdbId(externalKey) == null)
        {
            throw new NotFoundException("Movie Imdb Id n° " + externalKey + " Not found");
        }
        return movieJpaRepository.findByImdbId(externalKey);
    }

    @Override
    public Movie save(Movie movie) {
        return movieJpaRepository.save(movie);
    }
}
