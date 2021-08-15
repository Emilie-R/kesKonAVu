package fr.epita.kesKonAVu.application.user;

import fr.epita.kesKonAVu.domain.common.AlreadyExistingException;
import fr.epita.kesKonAVu.domain.common.DataFormatException;
import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.user.Member;
import fr.epita.kesKonAVu.domain.user.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;


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
        Mockito.when(memberRepositoryMock.findByPseudo(memberToCreate.getPseudo())).thenReturn(null);
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
        Mockito.when(memberRepositoryMock.findByPseudo(memberToCreate.getPseudo())).thenReturn(memberToCreate);

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
        Mockito.when(memberRepositoryMock.findByPseudo(memberToCreate2.getPseudo())).thenReturn(null);

        //When

        //Then
        Assertions.assertThrows(DataFormatException.class, ()-> memberService.createMember(memberToCreate1));
        Assertions.assertThrows(DataFormatException.class, ()-> memberService.createMember(memberToCreate2));
    }

    @Test
    public void findOneMember_should_failed_when_id_not_exists(){
        // Given
        final Long id = 1L;
        Mockito.when(memberRepositoryMock.findById(id)).thenReturn(Optional.empty());

        //When

        //Then
        Assertions.assertThrows(NotFoundException.class, ()-> memberService.findOne(id));

    }

    @Test
    public void findOneMember_should_call_findById_repository_once(){
        // Given
        final Long id = 1L;
        final Member member = new Member();
        member.setPseudo("emilie");
        member.setPassword("123456789");
        Mockito.when(memberRepositoryMock.findById(id)).thenReturn(Optional.of(member));

        //When
        final Member memberReturned = memberService.findOne(id);

        //Then
        Assertions.assertNotNull(memberReturned);
        Mockito.verify(memberRepositoryMock, Mockito.times(2)).findById(id);

    }


}
