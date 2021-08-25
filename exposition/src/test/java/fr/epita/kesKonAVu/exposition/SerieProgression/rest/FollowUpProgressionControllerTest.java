package fr.epita.kesKonAVu.exposition.SerieProgression.rest;

import fr.epita.kesKonAVu.SpringBootAppTest;
import fr.epita.kesKonAVu.application.SerieProgression.UpdateSerieProgressionApplicationService;
import fr.epita.kesKonAVu.domain.followUp.FollowUpRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = { SpringBootAppTest.class })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FollowUpProgressionControllerTest {
    @Autowired
    UpdateSerieProgressionApplicationService progressionService;
    @Autowired
    FollowUpRepository followUpRepository;
    @LocalServerPort
    private int port;
    private URL base;
    private String baseURL;
    private Long id;
    @Autowired
    private TestRestTemplate template;

    @BeforeAll
    public void setUp() {
        baseURL = "http://localhost:" + this.port + "/v1/progression/";
    }

    @Test
    public void editListEpisodeFollowUpsOKWhenExistingFollowUpIsGiven() throws IOException, URISyntaxException {
        //Given
        String idFollowUp = "3";
        Long idFollowUpL = 3L;
        URI uri = new URI(  baseURL + "edit/" + idFollowUp);
        HttpEntity<Long> request = new HttpEntity<>(idFollowUpL);

        //When
        HttpEntity<FollowUpProgressionDTO> result =
                template.exchange(uri, HttpMethod.GET, request, FollowUpProgressionDTO.class);

        //Then
        Assertions.assertEquals(5, result.getBody().getEpisodeFollowUpDTOList().size());


    }

    @Test
    public void saveListEpisodeFollowUpsOKWhenAFilledFollowProgressionDTOIsGiven() throws IOException, URISyntaxException {
        //Given
//
//        FollowUp followUp = followUpRepository.findByIdWithAllEpisodeFollowUps(3L);
//        FollowUpProgressionDTOMapper mapper = new FollowUpProgressionDTOMapper();
//        FollowUpProgressionDTO followUpProgession = mapper.mapToDto(followUp);
//        followUpProgession.setIdFollowUp(3L);
//        followUpProgession.getEpisodeFollowUpDTOList()
//                .stream()
//                .forEach(e -> e.setEpisodeStatusEnum(EpisodeStatusEnum.AVOIR)); // tous les épisodes "A VOIR"
//
//        URI uri = new URI(  baseURL + "save");
//        HttpEntity<FollowUpProgressionDTO> request = new HttpEntity<>(followUpProgession);
//
//        //When
//        HttpEntity<Long> result =
//                template.exchange(uri, HttpMethod.POST, request, Long.class);
//
//        //Then
//        Assertions.assertEquals(3, result.getBody());
//        FollowUp followUp2 = followUpRepository.findByIdWithAllEpisodeFollowUps(3L);
//        Long count = followUp2.getEpisodeFollowUps()
//                .stream()
//                .filter(e -> e.getStatus() == EpisodeStatusEnum.AVOIR)
//                .count();
//        Assertions.assertEquals(5,count);
//    }


}
