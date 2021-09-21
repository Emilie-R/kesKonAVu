package fr.epita.kesKonAVu.infrastructure.resource;

import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.resource.Movie;
import fr.epita.kesKonAVu.domain.resource.MovieRepository;
import fr.epita.kesKonAVu.domain.resource.Resource;

import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class MovieRepositoryTest {
    /*
     *  Tous les jeux d'essais de la BDD sont injectés dans la base H2 avec le fichier import.sql
     */


    @Autowired
    MovieRepository movieRepository;

    Movie resourceToSave = new Movie();

    @Test
    public void given_idResource_existing_Movie_findById_should_success () {
        //Given
        Long id = 1L;

        //When
        Movie result = movieRepository.findByIdResource(1L);

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Le Grand Bleu", result.getTitle());
        Assertions.assertEquals(ResourceTypeEnum.MOVIE, result.getResourceType());
        Assertions.assertEquals("tt0095250", result.getImdbId());
    }

    @Test
    public void given_idResource_unknown_member_findById_should_throw_NotFoundException () {
        //Given
        Long id = 0L;

        //When
        //Then
        Assertions.assertThrows(NotFoundException.class, () -> movieRepository.findByIdResource(id));
    }

    @Test
    public void given_new_movie_save_should_success() {
        //Given
        resourceToSave.setTitle("Le dernier métro");
        resourceToSave.setResourceType(ResourceTypeEnum.MOVIE);
        resourceToSave.setImdbId("12345678");

        //When
        Resource result = movieRepository.save(resourceToSave);

        //Then
        Assertions.assertNotNull(movieRepository.findByIdResource(result.getIdResource()));
        Assertions.assertEquals( resourceToSave.getTitle(),
                movieRepository.findByIdResource(result.getIdResource()).getTitle());
        Assertions.assertEquals( resourceToSave.getImdbId(),
                movieRepository.findByIdResource(result.getIdResource()).getImdbId());
    }

    @Test
    public void given_existing_movie_save_should_success() {
        //Given
        Movie resourceToSave = movieRepository.findByIdResource(1L);
        resourceToSave.setTitle("Tarzan");
        resourceToSave.setImdbId("12345678");

        //When
        Resource result = movieRepository.save(resourceToSave);

        //Then
        Assertions.assertEquals(resourceToSave.getTitle(), movieRepository.findByIdResource(1L).getTitle());
        Assertions.assertEquals(resourceToSave.getImdbId(),
                movieRepository.findByIdResource(1L).getImdbId());
        Assertions.assertEquals(resourceToSave.getIdResource(), result.getIdResource());
        Assertions.assertEquals(resourceToSave.getImdbId(), result.getImdbId());
    }

    @Test
    public void ShouldFindByTitleWhenMovieIsGiven() {
        //Given
        resourceToSave.setTitle("Le dernier métro");
        resourceToSave.setResourceType(ResourceTypeEnum.MOVIE);
        resourceToSave.setImdbId("12345678");
        resourceToSave.setIdResource(null);

        //When
        Movie result = movieRepository.save(resourceToSave);
        Movie retrieved = movieRepository.findByTitle(result.getTitle());

        //Then
        Assertions.assertEquals( resourceToSave.getTitle(), retrieved.getTitle());
    }
    @Test
    public void ShouldFindByExternalkeyWhenMovieIsGiven() {
        //Given
        resourceToSave.setTitle("Le dernier métro");
        resourceToSave.setResourceType(ResourceTypeEnum.MOVIE);
        resourceToSave.setImdbId("12345678");

        Movie resourceWitness = new Movie();
        resourceWitness.setTitle("Le premier métro");
        resourceWitness.setResourceType(ResourceTypeEnum.MOVIE);
        resourceWitness.setImdbId("87654321");

        //When
        Movie result = movieRepository.save(resourceToSave);
        Movie retrieved = movieRepository.findByImdbId(result.getImdbId());

        //Then
        Assertions.assertEquals(resourceToSave.getImdbId(), retrieved.getImdbId());
        Assertions.assertNotEquals(resourceWitness.getImdbId(), retrieved.getImdbId());
    }

}
