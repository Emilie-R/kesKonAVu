package fr.epita.kesKonAVu.domain.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
