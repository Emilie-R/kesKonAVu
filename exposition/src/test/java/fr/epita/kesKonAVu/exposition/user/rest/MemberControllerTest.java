package fr.epita.kesKonAVu.exposition.user.rest;

import fr.epita.kesKonAVu.application.user.MemberService;
import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.user.Member;
import fr.epita.kesKonAVu.domain.user.TypeRoleEnum;
import fr.epita.kesKonAVu.SpringBootAppTest;
import fr.epita.kesKonAVu.exposition.member.rest.MemberDTO;
import fr.epita.kesKonAVu.exposition.member.rest.MemberDTOLight;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { SpringBootAppTest.class })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MemberControllerTest {

    @MockBean
    MemberService memberService;

    @LocalServerPort
    private int port;
    private String baseURL;

    @Autowired
    private TestRestTemplate template;

    Member member = new Member();

    @BeforeAll
    public void setUp() {
        member.setPseudo("emilie");
        member.setPassword("12345678");
        member.setCreationDate(LocalDate.now());
        member.setRoles(new HashSet<>(Arrays.asList(TypeRoleEnum.ADMIN)));
        member.setIdMember(2L);

        baseURL = "http://localhost:" + this.port + "/api/v1/member/";
    }

    @Test
    public void createNewMember_should_success_given_new_Member() throws URISyntaxException {
        //Given
        URI uri = new URI(baseURL + "create");

        MemberDTOLight memberDTOToCreate = new MemberDTOLight();
        memberDTOToCreate.setPseudo("emilie");
        memberDTOToCreate.setPassword("12345678");
        memberDTOToCreate.setEmail("");

        Mockito.when(memberService.createMember(any(Member.class)))
                .thenReturn(member);

        HttpEntity<MemberDTOLight> request = new HttpEntity<>(memberDTOToCreate);

        // When
        ResponseEntity<MemberDTO> result = this.template.postForEntity(uri,request,MemberDTO.class);

        //Then
        Mockito.verify(memberService, Mockito.times(1)).createMember(any(Member.class));
        Assertions.assertEquals( HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals("emilie", result.getBody().getPseudo());
        Assertions.assertEquals(2L, result.getBody().getIdMember());
    }

    @Test
    public void getMemberAccountData_with_existing_idMember_should_success() throws URISyntaxException {
        //Given
        URI uri = new URI(baseURL + "2");
        Mockito.when(memberService.findOne(2L)).thenReturn(member);

        //When
        ResponseEntity<MemberDTO> result = this.template.getForEntity(uri, MemberDTO.class);

        //Then
        Mockito.verify(memberService, Mockito.times(1)).findOne(2L);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals( HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals("emilie", result.getBody().getPseudo());
        Assertions.assertEquals(2L, result.getBody().getIdMember());
    }

    @Test
    public void getMemberAccountData_with_unknown_idMember_should_throw_NotFoundException() throws URISyntaxException {
        //Given
        URI uri = new URI(baseURL + "111");
        Mockito.when(memberService.findOne(any())).thenThrow(NotFoundException.class);

        //When
        ResponseEntity<MemberDTO> result = this.template.getForEntity(uri, MemberDTO.class);

        //Then
        Mockito.verify(memberService, Mockito.times(1)).findOne(any());
        Assertions.assertNotEquals(result.getStatusCode(),HttpStatus.OK);
    }
//    @Test
//    public void given_member_findByIdWithAllItsResourceFollowUps_Successfull() throws URISyntaxException, UnirestException {
//        // Given Création du member à sauvegarder Puis à récupérer
//        final Member member = new Member();
//        final LocalDate dateCreation = LocalDate.now();
//
//        //member.setIdMember(30L);
//        member.setCreationDate(dateCreation);
//        member.setEmail("toto@gmail.com");
//        member.setPseudo("Petit Poisson Rouge");
//        member.addRole(TypeRoleEnum.USER);
//        member.addRole(TypeRoleEnum.ADMIN);
//        FollowUp fw1 = new FollowUp();
//        fw1.setIdFollowUp(15L);
//        fw1.setMember(member);
//        FollowUp fw2 = new FollowUp();
//        fw2.setIdFollowUp(20L);
//        fw2.setMember(member);
//        Set<FollowUp> set1 = Stream.of(fw1,fw2).collect(Collectors.toSet());
//        member.setFollowUps(set1);
//
//        // When Appel de la méthode à tester
//        Mockito.when(memberService.createMember(any())).thenReturn(member);
//        Mockito.when(memberService.findOne(any())).thenReturn(member);
//        final Member memberCreated = memberService.createMember(member);
//        //récupérer member avec ses FollowUp
//        URI uri = new URI(baseURL + "followup/" + member.getIdMember());
//
//        Unirest.setTimeouts(0, 0);
//        HttpResponse<MemberWithFollowupsDTO> response = Unirest.get("http://localhost:8080/api/v1/member/followup/"+member.getIdMember())
//                .asObject(MemberWithFollowupsDTO.class);
//
//        //Then
//
//        MemberWithFollowupsDTO result = response.getBody();
//        Assertions.assertEquals(2,result.getResourceFollowUpS().size());
//
//    }
}
