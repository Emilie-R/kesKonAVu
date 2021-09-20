package fr.epita.kesKonAVu.domain.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class MemberDomainServiceTest {

    @Autowired
    MemberDomainService memberDomainService;

    @Test
    public void checkMemberPseudo_with_pseudo_GT_6_char_should_return_true() {
        //Given
        String pseudo = "abcdef";

        //When
        //Then
        Assertions.assertTrue(memberDomainService.checkMemberPseudo(pseudo));
    }

    @Test
    public void checkMemberPseudo_with_pseudo_LT_6_char_should_return_false() {
        //Given
        String pseudo = "ab  cde";

        //When
        //Then
        Assertions.assertFalse(memberDomainService.checkMemberPseudo(pseudo));
    }

    @Test
    public void checkMemberPassword_with_password_GT_6_char_should_return_true() {
        //Given
        String password = "abcdef";

        //When
        //Then
        Assertions.assertTrue(memberDomainService.checkMemberPassword(password));
    }

    @Test
    public void checkMemberPassword_with_password_LT_6_char_should_return_false() {
        //Given
        String password = "ab  cde";

        //When
        //Then
        Assertions.assertFalse(memberDomainService.checkMemberPassword(password));
    }

    @Test
    public void duplicateMember_should_return_a_new_Member() {
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

        //When
        Member newMember = memberDomainService.duplicateMember(member);

        //Then
        Assertions.assertNotEquals(member, newMember);
        Assertions.assertEquals(member.getIdMember(), newMember.getIdMember());
        Assertions.assertEquals(member.getPseudo(), newMember.getPseudo());
        Assertions.assertEquals(member.getPassword(), newMember.getPassword());
        Assertions.assertEquals(member.getRoles(), newMember.getRoles());
        Assertions.assertEquals(member.getCreationDate(), newMember.getCreationDate());
        Assertions.assertEquals(member.getLastConnexionDateTime(), newMember.getLastConnexionDateTime());

    }
}
