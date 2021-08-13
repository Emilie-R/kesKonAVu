package fr.epita.kesKonAVu.exposition.resource.rest;

import fr.epita.kesKonAVu.application.resource.ResourceService;
import fr.epita.kesKonAVu.domain.resource.ExternalKey;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.exposition.SpringBootAppTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = { SpringBootAppTest.class })
public class ResourceControllerTest {
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @MockBean
    ResourceService resourceService;

    @Before
    public void setUp ( ) throws Exception {

        this.base = new URL("http://localhost:" + port + "/api/V1/movie/title/Godzilla"); //=> permet d'instancier les paramètre de connexion

    }

    // test du endPoint "findByTitle"
    @Test
    public void getResourceDTO ( ) throws Exception {

        // GIVEN
        final Resource movie = new Resource();
        //this.base = new URL("http://localhost:" + port + "/api/V1/movie/title/Godzilla"); //=> permet d'instancier les paramètre de connexion
        movie.setTitle("Friends 1");
        movie.setExternalKey(new ExternalKey());
        when(resourceService.findByTitle("Godzilla")).thenReturn(movie);

        // WHEN
        ResponseEntity<ResourceDTO> response = template.getForEntity(base.toString(),

                ResourceDTO.class);

        //Then
        //Verify request succeed
        Assert.assertEquals(200, response.getStatusCodeValue());
        String result = response.getBody().getTitle();
        Assert.assertTrue(result.equals(movie.getTitle()));

    }
}
