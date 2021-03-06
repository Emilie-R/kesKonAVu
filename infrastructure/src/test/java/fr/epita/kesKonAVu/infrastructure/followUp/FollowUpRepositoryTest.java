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
import java.time.LocalDateTime;

@DataJpaTest
public class FollowUpRepositoryTest {

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
        Assertions.assertEquals(LocalDateTime.now().toLocalDate(), result.getLastModificationDateTime().toLocalDate());
        Assertions.assertEquals("ID-1", result.getMember().getIdMember());
        Assertions.assertNotNull(result.getMember().getPassword());
        Assertions.assertEquals(1L, result.getResource().getIdResource());
        Assertions.assertEquals("Le Grand Bleu", result.getResource().getTitle());
        Assertions.assertEquals("tt0095250", result.getResource().getImdbId());
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
    public void given_new_followUp_save_should_success() {
        //Given
        Resource resource = resourceRepository.findById(1L);
        Member member = new Member();
        member.setIdMember("ID-1");
        member.setPseudo("emilie");

        FollowUp followUpToSave = new FollowUp();
        followUpToSave.setCreationDateTime(LocalDateTime.now());
        followUpToSave.setLastModificationDateTime(LocalDateTime.now());
        followUpToSave.setStatus(StatusEnum.AVOIR);
        followUpToSave.setResource(resource);
        followUpToSave.setMember(member);

        //Then
        FollowUp result = followUpRepository.save(followUpToSave);

        //When
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getIdFollowUp());
        Assertions.assertEquals(followUpToSave.getCreationDateTime(), result.getCreationDateTime());
        Assertions.assertEquals(followUpToSave.getStatus(), result.getStatus());
        Assertions.assertEquals("ID-1", result.getMember().getIdMember());
        Assertions.assertEquals(1L, result.getResource().getIdResource());
        Assertions.assertEquals("tt0095250", result.getResource().getImdbId());
    }

    @Test
    public void given_existing_member_and_resource_findByResourceAndMember_should_succes() {
        //Given
        Member member = memberRepository.findById("ID-1").orElse(new Member());
        Resource resource = resourceRepository.findById(1L);

        //When
        FollowUp result = followUpRepository.findByResourceAndMember(resource, member);

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result, followUpRepository.findById(result.getIdFollowUp()).orElse(new FollowUp()));

    }

    @Test
    public void given_no_matching_followUp_member_and_resource_findByResourceAndMember_should_return_null() {
        //Given
        Member member = new Member();
        member.setIdMember("XXXXXX");
        member.setPseudo("Fake Member");
        Resource resource = resourceRepository.findById(1L);

        //When
        FollowUp result = followUpRepository.findByResourceAndMember(resource, member);

        //Then
        Assertions.assertNull(result);
    }

    @Test
    public void given_existing_idFollowUp_deleteById_should_success() {
        //Given
        Long idFollowUp = 3L;

        //When
        followUpRepository.deleteById(idFollowUp);

        //Then
        Assertions.assertThrows(NotFoundException.class, ()-> followUpRepository.findById(idFollowUp));
    }

    @Test
    public void  getFollowUpWithitsEpisodeFollowUpsOK(){
        FollowUp f1 = followUpRepository.findByIdWithAllEpisodeFollowUps(3L);

        Assertions.assertEquals(5,f1.getEpisodeFollowUps().size());

    }
}
