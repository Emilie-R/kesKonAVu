package fr.epita.kesKonAVu.exposition.followUp.rest;

import fr.epita.kesKonAVu.SpringBootAppTest;
import fr.epita.kesKonAVu.application.followUp.FollowUpService;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = { SpringBootAppTest.class })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FollowUpControllerIntegrationTest {
    @LocalServerPort
    private int port;

    private URL base;

    private String baseURL;


    private Long id;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    FollowUpService resourceFollowUpService;

    @BeforeEach
    public void setUp() {

        baseURL = "http://localhost:" + this.port + "/v1/followup/";

    }


    @Test
    public void SaveNoteOnlywhenFollowUpIsGiven() throws URISyntaxException {
//        Given

        URI uri = new URI(baseURL+"update");
//        When
        FollowUpUpdateDTOLight in = new FollowUpUpdateDTOLight();
        in.setIdFollowUp(3L);
        in.setStatus(StatusEnum.AVOIR);
        in.setNote(40);
        HttpEntity<FollowUpUpdateDTOLight> request = new HttpEntity<>(in);
        ResponseEntity<String> result = this.template.exchange(uri, HttpMethod.PUT, request, String.class);
        //Then
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals("OK", result.getBody());
        FollowUp updated = resourceFollowUpService.findOne(in.getIdFollowUp());
        Assertions.assertEquals(40,updated.getNote());

    }

    @Test
    public void SaveStatusOnlywhenFollowUpIsGiven() throws URISyntaxException {
//        Given

        URI uri = new URI(baseURL+"update");
//        When
        FollowUpUpdateDTOLight in = new FollowUpUpdateDTOLight();
        in.setIdFollowUp(2L);
        in.setStatus(StatusEnum.AVOIR);
        in.setNote(0);
        HttpEntity<FollowUpUpdateDTOLight> request = new HttpEntity<>(in);
        ResponseEntity<String> result = this.template.exchange(uri, HttpMethod.PUT, request, String.class);
        //Then
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals("OK", result.getBody());
        FollowUp updated = resourceFollowUpService.findOne(in.getIdFollowUp());
        Assertions.assertEquals(StatusEnum.AVOIR,updated.getStatus());
    }
}
