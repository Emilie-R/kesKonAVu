package fr.epita.kesKonAVu.exposition.followUp.rest;

import fr.epita.kesKonAVu.SpringBootAppTest;
import fr.epita.kesKonAVu.application.followUp.FollowUpService;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FollowUpControllerTest {
    @LocalServerPort
    private int port;

    private URL base;
    private String baseURL;


    private String baseURL2;

    private Long id;

    @Autowired
    private TestRestTemplate template;

    @MockBean
    FollowUpService resourceFollowUpService;


    @BeforeEach
    public void setUp() {
        baseURL = "http://localhost:" + this.port + "/v1/followup/";
    }

    // test du endpoint SortResourcesListByDate
    @Test
    public void FindResourceFollowUpWhenIdIsGiven() throws IOException, URISyntaxException {
        //=> instancier les paramètre de connexion
        id = 1L;
        //        Given
        URI uri = new URI(baseURL+id);
        // liste de suivi
        FollowUp res1 = new FollowUp();
        res1.setStatus(StatusEnum.VU);
        res1.setIdFollowUp(1L);
        FollowUp res2 = new FollowUp();
        res2.setStatus(StatusEnum.AVOIR);
        res2.setIdFollowUp(2L);
        when(resourceFollowUpService.findOne(res1.getIdFollowUp())).thenReturn(res2);
        // WHEN
        HttpEntity<Long> request = new HttpEntity<>(id);
        ResponseEntity<FollowUp> result = this.template.exchange(uri, HttpMethod.GET, request, FollowUp.class);
        FollowUp response = result.getBody();

        //Then
        Assertions.assertEquals(response.getIdFollowUp(), res2.getIdFollowUp());
    }

    @Test
    public void createResourceFollowUpShouldSuccess() throws URISyntaxException {

        URI uri = new URI(baseURL+"create");
        FollowUpDTOLight res1 = new FollowUpDTOLight();
        res1.setStatus(StatusEnum.VU);
        FollowUp res2 = new FollowUp();
        res2.setStatus(StatusEnum.AVOIR);
        res2.setIdFollowUp(2L);
        Mockito.when(resourceFollowUpService.createNewFollowUp(any(FollowUp.class)))
                .thenReturn(res2);

        HttpEntity<FollowUpDTOLight> request = new HttpEntity<>(res1);

        // When
        ResponseEntity<FollowupDTO> result = this.template.exchange(uri, HttpMethod.POST, request, FollowupDTO.class);

        //Then
        Mockito.verify(resourceFollowUpService, Mockito.times(1))
                .createNewFollowUp(any(FollowUp.class));
        Assertions.assertEquals( HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(2L, result.getBody().getIdFollowUp());
    }

    @Test
    public void deleteFollowUp_when_id_is_given_should_call_followUpService_once() throws URISyntaxException {
        //Given
        String idFollowUp = "1";
        Long idFollowUpL = 1L;
        URI uri = new URI(  baseURL + idFollowUp);
        HttpEntity<Long> request = new HttpEntity<>(idFollowUpL);

        Mockito.when(resourceFollowUpService.deleteFollowUp(idFollowUpL)).thenReturn(idFollowUpL);

        //When
        HttpEntity<Long> result = template.exchange(uri, HttpMethod.DELETE, request, Long.class);

        //Then
        Mockito.verify(resourceFollowUpService, Mockito.times(1)).deleteFollowUp(1L);
    }

}
