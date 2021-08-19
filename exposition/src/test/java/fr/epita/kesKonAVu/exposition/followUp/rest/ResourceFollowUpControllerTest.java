package fr.epita.kesKonAVu.exposition.followUp.rest;

import fr.epita.kesKonAVu.application.followUp.ResourceFollowUpService;
import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import fr.epita.kesKonAVu.exposition.SpringBootAppTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = { SpringBootAppTest.class })
public class ResourceFollowUpControllerTest {
    @LocalServerPort
    private int port;

    private URL base;

    private Long id;

    @Autowired
    private TestRestTemplate template;

    @MockBean
    ResourceFollowUpService resourceFollowUpService;

    // test du endpoint SortResourcesListByDate
    @Test
    public void FindResourceFollowUpWhenIdIsGiven() throws IOException {
        //=> instancier les paramètre de connexion
        id = 1L;
        this.base = new URL("http://localhost:" + port + "/api/V1/followup/" + id);
        //GIVEN
        // liste de suivi
        ResourceFollowUp res1 = new ResourceFollowUp();
        res1.setStatus(StatusEnum.VU);
        res1.setIdFollowUp(1L);
        ResourceFollowUp res2 = new ResourceFollowUp();
        res2.setStatus(StatusEnum.AVOIR);
        res2.setIdFollowUp(2L);
        when(resourceFollowUpService.findOne(res1.getIdFollowUp())).thenReturn(res2);
        // WHEN
        ResourceFollowupDTO response = template
                .getForObject("http://localhost:" + port + "/api/V1/followup/" + id,
                ResourceFollowupDTO.class);

        //Then
        Assertions.assertTrue(response.getIdFollowUp().equals(res2.getIdFollowUp()));
    }

    @Test
    public void createResourceFollowUpShouldSuccess() throws URISyntaxException {

        URI uri = new URI("http://localhost:" + port + "/api/V1/followup/create");
        ResourceFollowupDTO res1 = new ResourceFollowupDTO();
        res1.setStatus(StatusEnum.VU);
        res1.setIdFollowUp(1L);
        ResourceFollowUp res2 = new ResourceFollowUp();
        res2.setStatus(StatusEnum.AVOIR);
        res2.setIdFollowUp(2L);
        Mockito.when(resourceFollowUpService.createResourceFollowUp(any(ResourceFollowUp.class)))
                .thenReturn(res2);

        HttpEntity<ResourceFollowupDTO> request = new HttpEntity<>(res1);

        // When
        ResponseEntity<ResourceFollowupDTO> result = this.template.postForEntity(uri,request,ResourceFollowupDTO.class);

        //Then
        Mockito.verify(resourceFollowUpService, Mockito.times(1))
                .createResourceFollowUp(any(ResourceFollowUp.class));
        Assertions.assertEquals( HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(2L, result.getBody().getIdFollowUp());
    }
}
