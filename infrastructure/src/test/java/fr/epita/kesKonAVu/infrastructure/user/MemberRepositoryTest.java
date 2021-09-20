package fr.epita.kesKonAVu.infrastructure.user;

import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.user.Member;
import fr.epita.kesKonAVu.domain.user.MemberRepository;
import fr.epita.kesKonAVu.domain.user.TypeRoleEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@DataJpaTest
public class MemberRepositoryTest {

    /*
    *  Tous les jeux d'essais de la BDD sont injectés dans la base H2 avec le fichier import.sql
    */

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void given_idMember_existing_member_findById_should_success() {
        // Given Avec un idMember identifié en BDD
        final String idMember = "ID-1";

        // When Récupération de l'objet attendu
        final Optional<Member> optionalMember = memberRepository.findById(idMember);
        Assertions.assertTrue(optionalMember.isPresent());
        Assertions.assertTrue(optionalMember.get() instanceof Member);

        // Then Contrôle du contenu des attributs
        final Member member = optionalMember.get();
        Assertions.assertEquals(member.getEmail(), "email@gmail.fr");
        Assertions.assertEquals(member.getPassword(), "123456");
        Assertions.assertEquals(member.getPseudo(), "emilie");
        Assertions.assertEquals(member.getRoles().size(), 2);

    }

    @Test
    public void given_unknown_idMember_for_member_findById_expected_empty_optional() {
        // Avec un idMember non identifié en BDD
        final String idMember = "XXXXXX";

        // Contrôle de l'objet attendu
        final Optional<Member> optionalMember = memberRepository.findById(idMember);
        Assertions.assertFalse(optionalMember.isPresent());
    }


    @Test
    public void given_pseudo_existing_member_findByPseudo_should_success() {
        //Given Avec un pseudo identifié en BDD
        final String pseudo = "emilie";

        //When Récupération de l'objet attendu
        final Optional<Member> member = memberRepository.findByPseudo(pseudo);

        //Then Contrôle du contenu des attributs
        Assertions.assertTrue(member.isPresent());
        Assertions.assertEquals(member.get().getIdMember(), "ID-1");
        Assertions.assertEquals(member.get().getEmail(), "email@gmail.fr");
        Assertions.assertEquals(member.get().getPassword(), "123456");
        Assertions.assertEquals(member.get().getPseudo(), "emilie");
        Assertions.assertEquals(member.get().getRoles().size(), 2);
    }

    @Test
    public void given_unknown_pseudo_for_member_findByPseudo_expected_empty_optional() {
        // Given Avec un pseudo inconnu en BDD
        final String pseudo = "Unknown";

        //When Contrôle de l'objet attendu
        final Optional<Member> member = memberRepository.findByPseudo(pseudo);

        //Then
        Assertions.assertFalse(member.isPresent());
    }

    @Test
    public void given_member_save_should_success() {
        // Given Création du member à sauvegarder
        final Member member = new Member();
        final LocalDate dateCreation = LocalDate.now();

        member.setCreationDate(dateCreation);
        member.setEmail("toto@gmail.com");
        member.setPseudo("Petit Poisson Rouge");
        member.addRole(TypeRoleEnum.USER);
        member.addRole(TypeRoleEnum.ADMIN);
        member.addRole(TypeRoleEnum.USER);

        // When Appel de la méthode à tester
        final Member memberCreated = memberRepository.save(member);

        //Then Contrôles
        Assertions.assertNotNull(memberCreated.getIdMember());
        Assertions.assertEquals(memberCreated.getRoles().size(),2);
        Assertions.assertTrue(memberRepository.findById(memberCreated.getIdMember()).isPresent());
    }

    @Test
    public void given_member_findByIdWithAllItsResourceFollowUps_Successfull() {
        // Given
        String idMember = "ID-1";

        //récupérer member avec ses FollowUp
        Member memberRetrieved = memberRepository.findByIdWithAllResourceFollowUps(idMember);

        //Then Contrôles
        Assertions.assertNotNull(memberRetrieved);
        Set<FollowUp> set2 = memberRetrieved.getFollowUps();
        Assertions.assertEquals(2,set2.size());
        set2.stream().forEach(e -> System.out.println(e.getIdFollowUp()));

    }
}
