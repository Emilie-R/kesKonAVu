package fr.epita.kesKonAVu.exposition.followUp.rest;

import fr.epita.kesKonAVu.SpringBootAppTest;
import fr.epita.kesKonAVu.application.followUp.FollowUpService;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = { SpringBootAppTest.class })
public class FollowUpControllerIntegrationTest {
    @LocalServerPort
    private int port;

    private URL base;

    private String baseURL;

    private String baseURL2;

    private Long id;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    FollowUpService resourceFollowUpService;

    @BeforeEach
    public void setUp() {

        baseURL = "http://localhost:" + this.port + "/v1/followup/note/";
    }


    @Test
    public void SaveNoteOnlywhenFollowUpIsGiven() throws URISyntaxException {
        //Given

        URI uri = new URI(baseURL + "2/35");
//        When
        ResponseEntity<String> result = this.template.exchange(uri, HttpMethod.PUT, null, String.class);
        //Then
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals( HttpStatus.OK, result.getStatusCode());
      Assertions.assertEquals("OK", result.getBody());

    }

    @Test
    public void SaveStatusOnlywhenFollowUpIsGiven() throws URISyntaxException {
        //Given

        URI uri = new URI(baseURL + "2/35");
//        When
        System.out.println("status avant : " + resourceFollowUpService.findOne(2L).getStatus());
        HttpEntity<StatusEnum> request = new HttpEntity<>(StatusEnum.VU);
        ResponseEntity<String> result = this.template.exchange(uri, HttpMethod.PUT, null, String.class);
        //Then
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals( HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals("OK", result.getBody());
        System.out.println("status avant : " + resourceFollowUpService.findOne(2L).getStatus());

    }
}
