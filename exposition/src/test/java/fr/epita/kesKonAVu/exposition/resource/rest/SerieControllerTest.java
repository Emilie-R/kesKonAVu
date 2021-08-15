package fr.epita.kesKonAVu.exposition.resource.rest;

import fr.epita.kesKonAVu.application.resource.SerieService;
import fr.epita.kesKonAVu.domain.resource.ExternalKey;
import fr.epita.kesKonAVu.domain.resource.Serie;
import fr.epita.kesKonAVu.exposition.SpringBootAppTest;
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
public class SerieControllerTest {
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @MockBean
    SerieService serieService;

    @BeforeEach
    public void setUp ( ) throws Exception {

        this.base = new URL("http://localhost:" + port + "/api/V1/serie/external/Jumanji"); //=> permet d'instancier les paramètre de connexion

    }

// test du endPoint "findByExternalKey"
    @Test
    public void getSerieDTO ( )  {

        // GIVEN
        final Serie createdSerie = new Serie();
        //this.base = new URL("http://localhost:" + port + "/api/V1/movie/title/Godzilla"); //=> permet d'instancier les paramètre de connexion
        createdSerie.setTitle("Friends 4");
        ExternalKey externalKey = new ExternalKey();
        externalKey.setResourceId("12356-azerty");
        createdSerie.setExternalKey(externalKey);
        when(serieService.findByExternalKey("Jumanji")).thenReturn(createdSerie);

        // WHEN
        ResponseEntity<SerieDTO> response = template.getForEntity(base.toString(),

                SerieDTO.class);

        //Then
        //Verify request succeed
        Assertions.assertEquals(200, response.getStatusCodeValue());
        String result = response.getBody().getExternalKey();
        Assertions.assertTrue(result.equals(createdSerie.getExternalKey().getResourceId()));

    }
}
