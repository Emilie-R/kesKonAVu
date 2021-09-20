package fr.epita.kesKonAVu.application.security;

import fr.epita.kesKonAVu.domain.common.InvalidCredentialsException;
import fr.epita.kesKonAVu.domain.user.Member;
import fr.epita.kesKonAVu.domain.user.MemberDomainService;
import fr.epita.kesKonAVu.domain.user.MemberRepository;
import fr.epita.kesKonAVu.domain.user.TypeRoleEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
public class AuthenticationServiceTest {

    @MockBean
    private MemberRepository memberRepositoryMock;

    @MockBean
    private MemberDomainService memberDomainMock;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationService authenticationService;

    @Test
    public void authenticateMember_given_existing_Member_should_success() {
        //Given
        Member member = new Member();
        member.setPseudo("emilie");
        member.setPassword("123456");
        Set<TypeRoleEnum> roles = new HashSet<>();
        roles.add(TypeRoleEnum.ADMIN);
        roles.add(TypeRoleEnum.USER);
        member.setRoles(roles);
        member.setLastConnexionDateTime(LocalDateTime.of(2021, 9,20,13,0));
        member.setCreationDate(LocalDate.of(2021, 9, 15));

        Member member2 = new Member();
        member2.setPseudo("emilie");
        member2.setPassword("123456");
        member2.setRoles(roles);
        member2.setLastConnexionDateTime(LocalDateTime.of(2021, 9,20,14,0));
        member2.setCreationDate(LocalDate.of(2021, 9, 15));

        Mockito.when(memberRepositoryMock.findByPseudo(member.getPseudo())).thenReturn(Optional.of(member));
        Mockito.when(memberDomainMock.duplicateMember(member)).thenReturn(member2);

        //When
        final Member memberResult = authenticationService.authenticateMember("emilie", "123456");

        //Then
        Assertions.assertEquals(member, memberResult);
        Assertions.assertNotEquals(member2.getLastConnexionDateTime(), LocalDateTime.of(2021, 9,20,14,0));
    }

    @Test
    public void authenticateMember_given_unknown_pseudo_should_throw_InvalidCredentialsException() {
        //Given
        Member member = new Member();
        member.setPseudo("emilie");
        member.setPassword("123456");
        Set<TypeRoleEnum> roles = new HashSet<>();
        roles.add(TypeRoleEnum.ADMIN);
        roles.add(TypeRoleEnum.USER);
        member.setRoles(roles);
        member.setLastConnexionDateTime(LocalDateTime.of(2021, 9,20,13,0));
        member.setCreationDate(LocalDate.of(2021, 9, 15));

        Mockito.when(memberRepositoryMock.findByPseudo(member.getPseudo())).thenReturn(Optional.empty());

        //When
        //Then
        Assertions.assertThrows(InvalidCredentialsException.class, ()-> authenticationService.authenticateMember("emilie", "123456"));
    }

    @Test
    public void authenticateMember_given_wrong_password_should_throw_InvalidCredentialsException() {
        //Given
        Member member = new Member();
        member.setPseudo("emilie");
        member.setPassword("123456");
        Set<TypeRoleEnum> roles = new HashSet<>();
        roles.add(TypeRoleEnum.ADMIN);
        roles.add(TypeRoleEnum.USER);
        member.setRoles(roles);
        member.setLastConnexionDateTime(LocalDateTime.of(2021, 9,20,13,0));
        member.setCreationDate(LocalDate.of(2021, 9, 15));

        Mockito.when(memberRepositoryMock.findByPseudo(member.getPseudo())).thenReturn(Optional.of(member));
        Mockito.when(authenticationManager.authenticate(Mockito.any())).thenThrow(BadCredentialsException.class);

        //When
        //Then
        Assertions.assertThrows(InvalidCredentialsException.class, ()-> authenticationService.authenticateMember("emilie", "123456"));
    }
}
