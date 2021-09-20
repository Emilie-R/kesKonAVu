package fr.epita.kesKonAVu.exposition.user.rest;

import fr.epita.kesKonAVu.SpringBootAppTest;
import fr.epita.kesKonAVu.application.security.AuthenticationService;
import fr.epita.kesKonAVu.config.security.jwt.JwtTokenManager;
import fr.epita.kesKonAVu.domain.user.Member;
import fr.epita.kesKonAVu.domain.user.TypeRoleEnum;
import fr.epita.kesKonAVu.exposition.member.rest.AuthenticationRequest;
import fr.epita.kesKonAVu.exposition.member.rest.MemberAuthenticatedDTO;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { SpringBootAppTest.class })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MemberAuthenticationControllerTest {

    @MockBean
    AuthenticationService authenticationServiceMock;

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
        member.setLastConnexionDateTime(LocalDateTime.of(2021, 9,20,13,0));
        member.setCreationDate(LocalDate.of(2021, 9, 15));
        member.setRoles(new HashSet<>(Arrays.asList(TypeRoleEnum.ADMIN, TypeRoleEnum.USER)));
        member.setIdMember("ID-2");

        baseURL = "http://localhost:" + this.port + "/v1/authenticate";
    }

    @Test
    public void authenticate_with_valid_credentials_should_success(){
        //Given
        UserDetails user = new User("ID-2","123", AuthorityUtils.NO_AUTHORITIES);
        Mockito.when(authenticationServiceMock.authenticateMember(member.getPseudo(), member.getPassword())).thenReturn(member);
        Mockito.when(userDetailsService.loadUserByUsername("ID-2")).thenReturn(user);

        HttpEntity<AuthenticationRequest> request = new HttpEntity<>(new AuthenticationRequest(member.getPseudo(), member.getPassword()));

        //When
        ResponseEntity<MemberAuthenticatedDTO> result = this.template.postForEntity(baseURL,request, MemberAuthenticatedDTO.class);

        //Then
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(member.getPseudo(), result.getBody().getPseudo());
        Assertions.assertEquals(member.getLastConnexionDateTime(), result.getBody().getLastConnexionDateTime());
        Assertions.assertNotNull(result.getBody().getJwtToken());
    }
}
