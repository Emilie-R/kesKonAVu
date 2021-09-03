package fr.epita.kesKonAVu.exposition.resource.rest;

import fr.epita.kesKonAVu.application.resource.SerieService;
import fr.epita.kesKonAVu.domain.resource.Serie;
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

        this.base = new URL("http://localhost:" + port + "/V1/serie/imdb/Jumanji"); //=> permet d'instancier les paramètre de connexion

    }

// test du endPoint "findByExternalKey"
    @Test
    public void getSerieDTOByImdbId_with_existing_ImdbId_should_success ( )  {

        // GIVEN
        final Serie createdSerie = new Serie();
        createdSerie.setTitle("Friends 4");
        createdSerie.setImdbId("xxxxx");
        when(serieService.findByImdbId("Jumanji")).thenReturn(createdSerie);

        // WHEN
        ResponseEntity<SerieDTO> response = template.getForEntity(base.toString(),

                SerieDTO.class);

        //Then
        //Verify request succeed
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(response.getBody().getImdbId(), createdSerie.getImdbId());

    }
}
