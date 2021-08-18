package fr.epita.kesKonAVu.exposition.followUp.rest;

import fr.epita.kesKonAVu.application.followUp.ResourceFollowUpService;
import fr.epita.kesKonAVu.application.user.MemberService;
import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import fr.epita.kesKonAVu.domain.user.Member;
import fr.epita.kesKonAVu.exposition.SpringBootAppTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    MemberService memberService;

    // test du endpoint SortResourcesListByDate
    @Test
    public void SendSortedResourcesListWhenCorrespondantFollowUpListIsGiven() throws MalformedURLException {
        //=> instancier les paramètre de connexion
        this.base = new URL("http://localhost:" + port + "/api/V1/followup/sortbyrating");
        //GIVEN
        // liste de suivi
        ResourceFollowUp res1 = new ResourceFollowUp();
        res1.setStatus(StatusEnum.VU);
        res1.setIdFollowUp(1L);
        ResourceFollowUp res2 = new ResourceFollowUp();
        res2.setStatus(StatusEnum.AVOIR);
        res2.setIdFollowUp(2L);
        Set<ResourceFollowUp> set1 = Stream.of(res1, res2)
                .collect(Collectors.toCollection(HashSet::new));

//        List<ResourceFollowUp> l1 = new ArrayList<>(set1);
//        RequestEntity<List<ResourceFollowUp>> request = RequestEntity
//                .post(new URI(base.toString()))
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(l1);
//        //WHEN
//        ResponseEntity<List<ResourceDTO>> response = template.exchange(, HttpMethod.GET, request, new ParameterizedTypeReference<List<ResourceFollowUp>>(){});;
//
//        response.getBody();


    }
//    // test du endPoint "getMapResourcesMember"
//    @Test
//    public void GetResourcesFollowUpListByStatusWhenMemberIsGiven ( ) throws Exception {
//        //=> instancier les paramètre de connexion
//        this.base = new URL("http://localhost:" + port + "/api/V1/followup/15");
//        // GIVEN
//        // utilisateur
//        final Member member = new Member();
//        member.setIdMember(15L);
//        member.setPseudo("Toctoc");
//        // liste de suivi
//        ResourceFollowUp res1 = new ResourceFollowUp();
//        res1.setStatus(StatusEnum.VU);
//        res1.setIdFollowUp(1L);
//        res1.setMember(member);
//        ResourceFollowUp res2 = new ResourceFollowUp();
//        res2.setStatus(StatusEnum.AVOIR);
//        res2.setIdFollowUp(2L);
//        res2.setMember(member);
//        Set<ResourceFollowUp> set1 = Stream.of(res1, res2)
//                .collect(Collectors.toCollection(HashSet::new));
//
//        member.setResourceFollowUps(set1);
//
//        when(memberService.findOne(15L)).thenReturn(member);
//
//        List<ResourceFollowUp> l1 = new ArrayList<>(member.getResourceFollowUps());
//
//        when(rfuService.findByMember(member)).thenReturn(l1);

//        // WHEN
//        ResponseEntity<Map<StatusEnum,List<ResourceFollowupDTO>>> response = template.getForEntity(base.toString(),
//
//                new ParameterizedTypeReference<Map<StatusEnum,List<ResourceFollowupDTO>>>(){});
//
//        //Then
//        //Verify request succeed
//        System.out.println(response.toString());;

//    }
}
