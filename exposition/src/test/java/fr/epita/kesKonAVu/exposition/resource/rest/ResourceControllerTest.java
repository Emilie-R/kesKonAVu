package fr.epita.kesKonAVu.exposition.resource.rest;

import fr.epita.kesKonAVu.application.resource.ResourceService;
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
public class ResourceControllerTest {
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @MockBean
    ResourceService resourceService;

    @BeforeEach
    public void setUp ( ) throws Exception {

        this.base = new URL("http://localhost:" + port + "/V1/movie/title/Godzilla"); //=> permet d'instancier les paramètre de connexion

    }

    // test du endPoint "findByTitle"
    @Test
    public void getResourceDTOByTitle_with_existing_title_should_success ( ) {

        // GIVEN
        final Resource movie = new Resource();
        movie.setTitle("Friends 1");
        movie.setImdbId("xxxxxx");
        when(resourceService.findMovieByTitle("Godzilla")).thenReturn(movie);

        // WHEN
        ResponseEntity<ResourceDTO> response = template.getForEntity(base.toString(),
                ResourceDTO.class);

        //Then
        //Verify request succeed
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(response.getBody().getTitle(), movie.getTitle());

    }
}
