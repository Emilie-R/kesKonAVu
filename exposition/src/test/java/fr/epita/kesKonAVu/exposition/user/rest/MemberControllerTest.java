package fr.epita.kesKonAVu.exposition.user.rest;

import fr.epita.kesKonAVu.application.user.MemberService;
import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.user.Member;
import fr.epita.kesKonAVu.domain.user.TypeRoleEnum;
import fr.epita.kesKonAVu.SpringBootAppTest;
import fr.epita.kesKonAVu.exposition.member.rest.MemberAuthenticatedDTO;
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
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

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

    @MockBean
    UserDetailsService userDetailsService;

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

        baseURL = "http://localhost:" + this.port + "/v1/member/";
    }

    @Test
    public void createNewMember_should_success_given_new_Member() throws URISyntaxException {
        //Given
        URI uri = new URI(baseURL + "create");

        MemberDTOLight memberDTOToCreate = new MemberDTOLight();
        memberDTOToCreate.setPseudo("emilie");
        memberDTOToCreate.setPassword("12345678");
        memberDTOToCreate.setEmail("");

        UserDetails user = new User("emilie","123", AuthorityUtils.NO_AUTHORITIES);

        Mockito.when(memberService.createMember(any(Member.class)))
                .thenReturn(member);
        Mockito.when(userDetailsService.loadUserByUsername("emilie")).
                thenReturn(user);

        HttpEntity<MemberDTOLight> request = new HttpEntity<>(memberDTOToCreate);

        // When
        ResponseEntity<MemberAuthenticatedDTO> result = this.template.postForEntity(uri,request, MemberAuthenticatedDTO.class);

        //Then
        Mockito.verify(memberService, Mockito.times(1)).createMember(any(Member.class));
        Assertions.assertEquals( HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals("emilie", result.getBody().getPseudo());
        Assertions.assertEquals(2L, result.getBody().getIdMember());
        Assertions.assertNotNull(result.getBody().getJwtToken());
    }

    @Test
    public void getMemberAccountData_with_existing_pseudo_should_success() throws URISyntaxException {
        //Given
        URI uri = new URI(baseURL + "toto");
        Mockito.when(memberService.findOne("toto")).thenReturn(member);

        //When
        ResponseEntity<MemberDTO> result = this.template.getForEntity(uri, MemberDTO.class);

        //Then
        Mockito.verify(memberService, Mockito.times(1)).findOne("toto");
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals( HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals("emilie", result.getBody().getPseudo());
        Assertions.assertEquals(2L, result.getBody().getIdMember());
    }

    @Test
    public void getMemberAccountData_with_unknown_pseudo_should_throw_NotFoundException() throws URISyntaxException {
        //Given
        URI uri = new URI(baseURL + "unknown");
        Mockito.when(memberService.findOne(any())).thenThrow(NotFoundException.class);

        //When
        ResponseEntity<MemberDTO> result = this.template.getForEntity(uri, MemberDTO.class);

        //Then
        Mockito.verify(memberService, Mockito.times(1)).findOne(any());
        Assertions.assertNotEquals(result.getStatusCode(),HttpStatus.OK);
    }
//    @Test
//    public void given_member_findByIdWithAllItsResourceFollowUps_Successfull() throws URISyntaxException {
//        // Given Création du member à sauvegarder Puis à récupérer
//        final Member member = new Member();
//        final LocalDate dateCreation = LocalDate.now();
//
//        member.setIdMember(30L);
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
//        member.setResourceFollowUps(set1);
//
//        // When Appel de la méthode à tester
//        final Member memberCreated = memberService2.createMember(member);
//        //récupérer member avec ses FollowUp
//        URI uri = new URI(baseURL + "followup/" + member.getIdMember());
//        //Mockito.when(memberService.findOne(any())).thenThrow(NotFoundException.class);
//
//        //When
//        ResponseEntity<MemberWithResourceFollowupsDTO> response = this.template.getForEntity(uri, MemberWithResourceFollowupsDTO.class);
//
//        //Then
//        Assertions.assertNotEquals(response.getStatusCode(),HttpStatus.OK);
//        MemberWithResourceFollowupsDTO result = response.getBody();
//        Assertions.assertEquals(2,result.getResourceFollowUpS().size());
//
//    }
}
