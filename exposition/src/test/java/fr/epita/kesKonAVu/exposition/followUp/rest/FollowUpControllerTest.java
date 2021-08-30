package fr.epita.kesKonAVu.exposition.followUp.rest;

import fr.epita.kesKonAVu.SpringBootAppTest;
import fr.epita.kesKonAVu.application.followUp.FollowUpService;
import fr.epita.kesKonAVu.config.security.jwt.JwtTokenManager;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

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

    private Long id;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    JwtTokenManager jwtTokenUtil;

    @Autowired
    FollowUpMapper followUpMapper;

    @MockBean
    FollowUpService followUpService;


    @BeforeAll
    public void setUp() {
        baseURL = "http://localhost:" + this.port + "/v1/followup/";
    }

    // test du endpoint SortResourcesListByDate
    @Test
    public void FindFollowUpWhenIdIsGiven() throws IOException, URISyntaxException {
        //=> instancier les paramètre de connexion
        id = 1L;
        this.base = new URL("http://localhost:" + port + "/api/V1/followup/" + id);

        // GIVEN
        FollowUp res1 = new FollowUp();
        res1.setStatus(StatusEnum.VU);
        res1.setIdFollowUp(1L);
        FollowUp res2 = new FollowUp();
        res2.setStatus(StatusEnum.AVOIR);
        res2.setIdFollowUp(2L);

        String idFollowUp = "1";
        Long idFollowUpL = 1L;
        URI uri = new URI(  baseURL + idFollowUp);
        HttpEntity<Long> request = new HttpEntity<>(idFollowUpL);

        //When
        when(followUpService.findOne(any())).thenReturn(res2);
        HttpEntity<FollowUpDTO> result = template.exchange(uri, HttpMethod.GET, request, FollowUpDTO.class);

        //Then
        Assertions.assertEquals(res2.getIdFollowUp(), result.getBody().getIdFollowUp());
        Mockito.verify(followUpService, Mockito.times(1)).findOne(1L);

    }

    @Test
    public void create_followUp_should_success() throws URISyntaxException {
        //Given => instancier les paramètre de connexion
        // - Création du JWT pour identifier le member
        UserDetails user = new User("ID-2","123", AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"));
        final String token = jwtTokenUtil.generateToken(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        // - Création du body de la requête
        FollowUpDTOLight res1 = new FollowUpDTOLight();
        res1.setStatus(StatusEnum.VU);
        res1.setResourceDTOLight(new ResourceDTOLight());
        FollowUp res1Transformed = followUpMapper.mapToEntity(res1);
        res1Transformed.setStatus(StatusEnum.AVOIR); // de VU -> à AVOIR
        Mockito.when(followUpService.createNewFollowUp(any())).thenReturn(res1Transformed);

        URI uri = new URI(  baseURL + "create");
        HttpEntity<FollowUpDTOLight> request = new HttpEntity<>(res1, headers);

        //When
        ResponseEntity<FollowUpDTO> result = template.exchange(uri, HttpMethod.POST, request, FollowUpDTO.class);

        //Then
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(StatusEnum.AVOIR, result.getBody().getStatus());
    }

    @Test
    public void deleteFollowUp_when_id_is_given_should_call_followUpService_once() throws URISyntaxException {
        //Given
        String idFollowUp = "1";
        Long idFollowUpL = 1L;
        URI uri = new URI(  baseURL + idFollowUp);
        HttpEntity<Long> request = new HttpEntity<>(idFollowUpL);

        Mockito.when(followUpService.deleteFollowUp(idFollowUpL)).thenReturn(idFollowUpL);

        //When
        HttpEntity<Long> result = template.exchange(uri, HttpMethod.DELETE, request, Long.class);

        //Then
        Assertions.assertEquals(idFollowUpL, result.getBody());
        Mockito.verify(followUpService, Mockito.times(1)).deleteFollowUp(1L);
    }

}
