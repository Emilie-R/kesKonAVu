package fr.epita.kesKonAVu.exposition.user.rest;

import fr.epita.kesKonAVu.application.user.MemberService;
import fr.epita.kesKonAVu.domain.user.Member;
import fr.epita.kesKonAVu.domain.user.TypeRoleEnum;
import fr.epita.kesKonAVu.exposition.SpringBootAppTest;
import fr.epita.kesKonAVu.exposition.member.rest.MemberController;
import fr.epita.kesKonAVu.exposition.member.rest.MemberDTO;
import fr.epita.kesKonAVu.exposition.member.rest.MemberDTOLight;
import fr.epita.kesKonAVu.exposition.member.rest.MemberMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { SpringBootAppTest.class })
public class MemberControllerTest {

    @MockBean
    MemberService memberService;

    @LocalServerPort
    private int port;
    private String baseURL;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() {
        baseURL = "http://localhost:" + this.port + "/api/v1/member/";
    }

    @Test
    public void createNewMember_should_success_given_new_Member() throws URISyntaxException {
        // TODO
        //Given
        URI uri = new URI(baseURL + "create");

        MemberDTOLight memberDTOToCreate = new MemberDTOLight();
        memberDTOToCreate.setPseudo("emilie");
        memberDTOToCreate.setPassword("12345678");
        memberDTOToCreate.setEmail("toto@gmail.fr");

        Member memberToCreate = new Member();
        memberToCreate.setPseudo("emilie");
        memberToCreate.setPassword("12345678");
        memberToCreate.setEmail("toto@gmail.fr");

        Member member = new Member();
        member.setPseudo("emilie");
        member.setPassword("12345678");
        member.setCreationDate(LocalDate.now());
        member.setRoles(new HashSet<>(Arrays.asList(TypeRoleEnum.ADMIN)));
        member.setIdMember(2L);

        Mockito.when(memberService.createMember(memberToCreate))
                .thenReturn(member);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        HttpEntity<MemberDTOLight> request = new HttpEntity<>(memberDTOToCreate, headers);
        System.out.println(baseURL + "create");

        // When
        ResponseEntity<MemberDTO> result = this.template.postForEntity(uri,request,MemberDTO.class);

        //Then
        //Mockito.verify(memberService, Mockito.times(1)).createMember(memberMapper.mapLightToEntity(memberDTOToCreate));
        System.out.println((result.getStatusCode()));
    }


}
