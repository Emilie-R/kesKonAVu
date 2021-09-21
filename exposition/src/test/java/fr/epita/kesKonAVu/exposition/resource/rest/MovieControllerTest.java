package fr.epita.kesKonAVu.exposition.resource.rest;

import fr.epita.kesKonAVu.application.resource.MovieService;
import fr.epita.kesKonAVu.domain.resource.Movie;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.SpringBootAppTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URL;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = { SpringBootAppTest.class })
public class MovieControllerTest {
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @MockBean
    MovieService movieService;

    @BeforeEach
    public void setUp ( ) throws Exception {

        this.base = new URL("http://localhost:" + port + "/v1/movie/"); //=> permet d'instancier les paramètre de connexion

    }

    // test du endPoint "findByTitle"
    @Test
    public void getMovieDTOByTitle_with_existing_title_should_success ( ) {

        // GIVEN
        final Movie movie = new Movie();
        movie.setTitle("Friends 1");
        movie.setImdbId("xxxxxx");
        when(movieService.findByTitle("Godzilla")).thenReturn(movie);

        // WHEN
        ResponseEntity<MovieDTO> response = template.getForEntity(base.toString()+ "title/Godzilla",
                MovieDTO.class);

        //Then
        //Verify request succeed
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(response.getBody().getTitle(), movie.getTitle());

    }

    // test du endPoint "findByTitle"
    @Test
    public void getMovieDTOByImdbId_with_existing_title_should_success ( ) {

        // GIVEN
        final Movie movie = new Movie();
        movie.setTitle("Friends 1");
        movie.setImdbId("123456");
        when(movieService.findByImdbId("123456")).thenReturn(movie);

        // WHEN
        ResponseEntity<MovieDTO> response = template.getForEntity(base.toString() + "imdb/123456",
                MovieDTO.class);

        //Then
        //Verify request succeed
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(response.getBody().getTitle(), movie.getTitle());

    }
}
