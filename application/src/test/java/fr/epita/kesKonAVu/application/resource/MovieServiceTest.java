package fr.epita.kesKonAVu.application.resource;


import fr.epita.kesKonAVu.SpringBootAppTest;
import fr.epita.kesKonAVu.domain.resource.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = { SpringBootAppTest.class })
public class MovieServiceTest {
    @Autowired
    MovieService movieService;
    @MockBean
    MovieRepository movieRepository;

    @Test
    public void find_another_movie_When_Title_Does_Not_Exists(){
        // GIVEN
        final Movie movie = new Movie();
        movie.setTitle("Friends 1");
        when(movieRepository.findByTitle("Hello")).thenReturn(movie);

        // WHEN
        final Movie createdMovie = movieService.findByTitle("Hello");

        //Then
        assertThat(createdMovie).isNotNull();
        Assertions.assertEquals("Friends 1", createdMovie.getTitle());
    }

    @Test
    public void find_movie_When_IdIsGiven(){
        // GIVEN
        final Movie movie = new Movie();
        movie.setTitle("Friends 1");
        movie.setIdResource(1L);
        when(movieRepository.findByIdResource(3L)).thenReturn(movie);

        // WHEN
        final Movie createdMovie = movieService.findByIdResource(3L);

        //Then
        assertThat(createdMovie).isNotNull();
        Assertions.assertEquals(1L, createdMovie.getIdResource());
    }

    @Test
    public void find_another_movie_When_ExternalKey_Is_Given(){
        // GIVEN
        final Movie movie = new Movie();
        movie.setTitle("Friends 1");
        movie.setImdbId("123arc");
        when(movieRepository.findByImdbId("Hello")).thenReturn(movie);

        // WHEN
        final Resource createdMovie = movieService.findByImdbId("Hello");

        //Then
        assertThat(createdMovie).isNotNull();
        Assertions.assertEquals(createdMovie.getImdbId(), movie.getImdbId());
    }
}
