package fr.epita.kesKonAVu.application.user;

import fr.epita.kesKonAVu.domain.common.AlreadyExistingException;
import fr.epita.kesKonAVu.domain.common.DataFormatException;
import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import fr.epita.kesKonAVu.domain.user.Member;
import fr.epita.kesKonAVu.domain.user.MemberRepository;
import fr.epita.kesKonAVu.domain.user.TypeRoleEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringBootTest
public class MemberServiceTest {

    @MockBean
    private MemberRepository memberRepositoryMock;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberService memberService;

    @Test
    public void createMember_should_success_when_not_already_exists(){
        //Given
        Member memberToCreate = new Member();
        final String password = "12345678";
        memberToCreate.setPseudo("emilie");
        memberToCreate.setPassword(password);
        Mockito.when(memberRepositoryMock.findByPseudo(memberToCreate.getPseudo())).thenReturn(Optional.empty());
        Mockito.when(memberRepositoryMock.save(memberToCreate)).thenReturn(memberToCreate);

        // When
        final Member memberCreated = memberService.createMember(memberToCreate);

        //Then
        Assertions.assertNotNull(memberCreated);
        Mockito.verify(memberRepositoryMock, Mockito.times(1)).save(memberToCreate);
        Mockito.verify(memberRepositoryMock, Mockito.times(1)).findByPseudo(memberToCreate.getPseudo());
    }

    @Test
    public void createMember_should_failed_when_pseudo_already_exists(){
        //Given
        final Member memberToCreate = new Member();
        memberToCreate.setPseudo("emilie");
        memberToCreate.setPassword("12345678");
        Mockito.when(memberRepositoryMock.findByPseudo(memberToCreate.getPseudo())).thenReturn(Optional.of(memberToCreate));

        //When

        //Then
        Assertions.assertThrows(AlreadyExistingException.class, ()-> memberService.createMember(memberToCreate));
    }

    @Test
    public void createMember_should_failed_when_data_invalid(){
        //Given
        final Member memberToCreate1 = new Member();
        memberToCreate1.setPseudo("mimi");
        memberToCreate1.setPassword("12345678");

        final Member memberToCreate2 = new Member();
        memberToCreate2.setPseudo("emilie");
        memberToCreate2.setPassword("12345");
        Mockito.when(memberRepositoryMock.findByPseudo(memberToCreate2.getPseudo())).thenReturn(Optional.empty());

        //When

        //Then
        Assertions.assertThrows(DataFormatException.class, ()-> memberService.createMember(memberToCreate1));
        Assertions.assertThrows(DataFormatException.class, ()-> memberService.createMember(memberToCreate2));
    }

    @Test
    public void findOneMember_should_failed_when_id_not_exists(){
        // Given
        final String pseudo = "unknown";
        Mockito.when(memberRepositoryMock.findByPseudo(pseudo)).thenReturn(Optional.empty());

        //When
        //Then
        Assertions.assertThrows(NotFoundException.class, ()-> memberService.findOne(pseudo));

    }

    @Test
    public void findOneMember_should_call_findById_repository_once(){
        // Given
        final String pseudo = "emilie";
        final Member member = new Member();
        member.setPseudo("emilie");
        member.setPassword("123456789");
        Mockito.when(memberRepositoryMock.findByPseudo(pseudo)).thenReturn(Optional.of(member));

        //When
        final Member memberReturned = memberService.findOne(pseudo);

        //Then
        Assertions.assertNotNull(memberReturned);
        Mockito.verify(memberRepositoryMock, Mockito.times(1)).findByPseudo(pseudo);

    }

    @Test
    public void Given_MemberWithResourceFollowUps_RetrieveSuccessfull() {

        // Given Création du member à sauvegarder Puis à récupérer
        final Member member = new Member();
        final LocalDate dateCreation = LocalDate.now();
        member.setCreationDate(dateCreation);
        member.setEmail("toto@gmail.com");
        member.setPseudo("Petit Poisson Rouge");
        member.addRole(TypeRoleEnum.USER);
        member.addRole(TypeRoleEnum.ADMIN);
        FollowUp fw1 = new FollowUp();
        fw1.setIdFollowUp(15L);
        fw1.setMember(member);
        FollowUp fw2 = new FollowUp();
        fw2.setIdFollowUp(20L);
        fw2.setMember(member);
        Set<FollowUp> set1 = Stream.of(fw1,fw2).collect(Collectors.toSet());
        member.setFollowUps(set1);

        //When
        Mockito.when(memberRepositoryMock.findByIdWithAllResourceFollowUps(member.getIdMember()))
                .thenReturn(member); // accès aux données testées OK dans infra/src/test
        final Member memberReturned = memberService.findByIdWithAllResourceFollowUps(member.getIdMember(),Optional.empty());
        Assertions.assertEquals(2,memberReturned.getFollowUps().size());

    }

    @Test
    public void Given_Movie_MemberWithResourceFollowUps_RetrieveSuccessfull() {

        // Given Création du member à sauvegarder Puis à récupérer
        final Member member = new Member();
        final LocalDate dateCreation = LocalDate.now();
        member.setCreationDate(dateCreation);
        member.setEmail("toto@gmail.com");
        member.setPseudo("Petit Poisson Rouge");
        member.addRole(TypeRoleEnum.USER);
        member.addRole(TypeRoleEnum.ADMIN);
        Resource r1 = new Resource();
        r1.setResourceType(ResourceTypeEnum.MOVIE);
        Resource r2 = new Resource();
        r2.setResourceType(ResourceTypeEnum.SERIE);
        FollowUp fw1 = new FollowUp();
        fw1.setIdFollowUp(15L);
        fw1.setResource(r1);
        fw1.setMember(member);
        FollowUp fw2 = new FollowUp();
        fw2.setIdFollowUp(20L);
        fw2.setResource(r2);
        fw2.setMember(member);
        Set<FollowUp> set1 = Stream.of(fw1,fw2).collect(Collectors.toSet());
        member.setFollowUps(set1);

        //When
        Mockito.when(memberRepositoryMock.findByIdWithAllResourceFollowUps(member.getIdMember()))
                .thenReturn(member); // accès aux données testées OK dans infra/src/test
        final Member memberReturned = memberService.findByIdWithAllResourceFollowUps(member.getIdMember(),Optional.of(ResourceTypeEnum.MOVIE));
        Assertions.assertEquals(1,memberReturned.getFollowUps().size());

    }

}
