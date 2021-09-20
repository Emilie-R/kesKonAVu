package fr.epita.kesKonAVu.exposition.user.rest;

import fr.epita.kesKonAVu.application.user.MemberService;
import fr.epita.kesKonAVu.config.security.jwt.JwtTokenManager;
import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.user.Member;
import fr.epita.kesKonAVu.domain.user.TypeRoleEnum;
import fr.epita.kesKonAVu.SpringBootAppTest;
import fr.epita.kesKonAVu.exposition.member.rest.MemberAuthenticatedDTO;
import fr.epita.kesKonAVu.exposition.member.rest.MemberDTO;
import fr.epita.kesKonAVu.exposition.member.rest.MemberDTOLight;
import fr.epita.kesKonAVu.exposition.member.rest.MemberWithFollowupsDTO;
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
import org.springframework.security.core.userdetails.UserDetailsService;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { SpringBootAppTest.class })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MemberControllerTest {

    @MockBean
    MemberService memberService;

    @MockBean
    UserDetailsService userDetailsService;

    @Autowired
    JwtTokenManager jwtTokenUtil;

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
        member.setIdMember("ID-2");

        FollowUp fw1 = new FollowUp();
        fw1.setIdFollowUp(15L);
        fw1.setMember(member);
        FollowUp fw2 = new FollowUp();
        fw2.setIdFollowUp(20L);
        fw2.setMember(member);
        Set<FollowUp> set1 = Stream.of(fw1,fw2).collect(Collectors.toSet());
        member.setFollowUps(set1);

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


        UserDetails user = new User("ID-2","123", AuthorityUtils.NO_AUTHORITIES);

        Mockito.when(memberService.createMember(any(Member.class)))
                .thenReturn(member);
        Mockito.when(userDetailsService.loadUserByUsername("ID-2")).
                thenReturn(user);

        HttpEntity<MemberDTOLight> request = new HttpEntity<>(memberDTOToCreate);

        // When
        ResponseEntity<MemberAuthenticatedDTO> result = this.template.postForEntity(uri,request, MemberAuthenticatedDTO.class);
        //Then
        Mockito.verify(memberService, Mockito.times(1)).createMember(any(Member.class));
        Assertions.assertEquals( HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(member.getPseudo(), result.getBody().getPseudo());
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
        Assertions.assertEquals(member.getPseudo(), result.getBody().getPseudo());
    }

    @Test
    public void getMemberAccountData_with_unknown_pseudo_should_throw_Unauthorized() throws URISyntaxException {
        //Given
        URI uri = new URI(baseURL + "unknown");
        Mockito.when(memberService.findOne(any())).thenThrow(NotFoundException.class);

        //When
        ResponseEntity<MemberDTO> result = this.template.getForEntity(uri, MemberDTO.class);

        //Then
        Mockito.verify(memberService, Mockito.times(1)).findOne(any());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void given_member_findByIdWithAllItsResourceFollowUps_Successfull() throws URISyntaxException {
        // Given - Création du token
        UserDetails user = new User("ID-2","123", AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"));
        final String token = jwtTokenUtil.generateToken(user);
        URI uri = new URI(baseURL + "followups");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        Mockito.when(memberService.findByIdWithAllResourceFollowUps(member.getIdMember(), Optional.empty())).thenReturn(member);

        // When Appel de la méthode à tester
        ResponseEntity<MemberWithFollowupsDTO> response = this.template
                .exchange(uri, HttpMethod.GET, request, MemberWithFollowupsDTO.class);

        //Then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(2,response.getBody().getResourceFollowUpS().size());
    }

    @Test
    public void given_unkonwn_member_findByIdWithAllItsResourceFollowUps_should_throw_exception() throws URISyntaxException {
        // Given
        UserDetails user = new User("ID-2","123", AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"));
        final String token = jwtTokenUtil.generateToken(user);
        URI uri = new URI(baseURL + "followups");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        Mockito.when(memberService.findByIdWithAllResourceFollowUps(member.getIdMember(), Optional.empty())).thenThrow(new NotFoundException());

        // When Appel de la méthode à tester
        ResponseEntity<MemberWithFollowupsDTO> response = this.template
                .exchange(uri, HttpMethod.GET, request, MemberWithFollowupsDTO.class);

        //Then
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
