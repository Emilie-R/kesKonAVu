package fr.epita.kesKonAVu.infrastructure.followUp;

import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.followUp.FollowUpRepository;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import fr.epita.kesKonAVu.domain.resource.*;
import fr.epita.kesKonAVu.domain.user.Member;
import fr.epita.kesKonAVu.domain.user.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
public class FollowUpTest {

    /*
     *  Tous les jeux d'essais de la BDD sont injectés dans la base H2 avec le fichier import.sql
     */

    @Autowired
    FollowUpRepository followUpRepository;

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void given_existing_idFollowUp_findById_should_success() {
        //Given
        Long idFollowUp = 1L;
        FollowUp result = new FollowUp();
        //Then
        if (followUpRepository.findById(idFollowUp).isPresent()) {
         result = followUpRepository.findById(idFollowUp).get(); }

        //When
        Assertions.assertEquals(StatusEnum.VU, result.getStatus());
        Assertions.assertEquals(LocalDate.now(), result.getLastModificationDate());
        Assertions.assertEquals(1L, result.getMember().getIdMember());
        Assertions.assertNotNull(result.getMember().getPassword());
        Assertions.assertEquals(1L, result.getResource().getIdResource());
        Assertions.assertEquals("Le Grand Bleu", result.getResource().getTitle());
        Assertions.assertEquals("tt0095250", result.getResource().getExternalKey());
    }

    @Test
    public void given_unknown_idFollowUp_findById_should_throw_NotFoundException() {
        //Given
        Long idFollowUp = 0L;

        //Then
        //When
        Assertions.assertThrows(NotFoundException.class, ()-> followUpRepository.findById(idFollowUp));
    }

    @Test
    public void given_new_resourceFollowUp_save_should_success() {
        //Given
        Resource resource = resourceRepository.findById(1L);
        Member member = new Member();
        member.setIdMember(1L);
        member.setPseudo("emilie");

        FollowUp resourceFollowUpToSave = new FollowUp();
        resourceFollowUpToSave.setCreationDate(LocalDate.now());
        resourceFollowUpToSave.setLastModificationDate(LocalDate.now());
        resourceFollowUpToSave.setStatus(StatusEnum.AVOIR);
        resourceFollowUpToSave.setResource(resource);
        resourceFollowUpToSave.setMember(member);

        //Then
        FollowUp result = followUpRepository.save(resourceFollowUpToSave);

        //When
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getIdFollowUp());
        Assertions.assertEquals(resourceFollowUpToSave.getCreationDate(), result.getCreationDate());
        Assertions.assertEquals(resourceFollowUpToSave.getStatus(), result.getStatus());
        Assertions.assertEquals(1L, result.getMember().getIdMember());
        Assertions.assertEquals(1L, result.getResource().getIdResource());
        Assertions.assertEquals("tt0095250", result.getResource().getExternalKey());
    }

    @Test
    public void given_existing_member_and_resource_findByResourceAndMember_should_succes() {
        //Given
        Member member = memberRepository.findById(1L).get();
        Resource resource =resourceRepository.findById(1L);

        //When
        FollowUp result = followUpRepository.findByResourceAndMember(resource, member);

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result, followUpRepository.findById(result.getIdFollowUp()).get());

    }

    @Test
    public void given_no_matching_followUp_member_and_resource_findByResourceAndMember_should_return_null() {
        //Given
        Member member = new Member();
        member.setIdMember(0L);
        member.setPseudo("Fake Member");
        Resource resource =resourceRepository.findById(1L);

        //When
        FollowUp result = followUpRepository.findByResourceAndMember(resource, member);

        //Then
        Assertions.assertNull(result);

    }
}
