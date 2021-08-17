package fr.epita.kesKonAVu.infrastructure.followUp;

import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;
import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUpRepository;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import fr.epita.kesKonAVu.domain.resource.*;
import fr.epita.kesKonAVu.domain.user.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
public class ResourceFollowUpTest {

    /*
     *  Tous les jeux d'essais de la BDD sont injectés dans la base H2 avec le fichier import.sql
     */

    @Autowired
    ResourceFollowUpRepository resourceFollowUpRepository;

    @Autowired
    ResourceRepository resourceRepository;

    @Test
    public void given_existing_idFollowUp_findById_should_success() {
        //Given
        Long idFollowUp = 1L;

        //Then
        ResourceFollowUp result = resourceFollowUpRepository.findById(1L);

        //When
        Assertions.assertEquals(StatusEnum.VU, result.getStatus());
        Assertions.assertEquals(LocalDate.now(), result.getLastModificationDate());
        Assertions.assertEquals(1L, result.getMember().getIdMember());
        Assertions.assertNotNull(result.getMember().getPassword());
        Assertions.assertEquals(1L, result.getResource().getIdResource());
        Assertions.assertEquals("Le Grand Bleu", result.getResource().getTitle());
        Assertions.assertEquals("tt0095250", result.getResource().getExternalKey().getResourceId());
    }

    @Test
    public void given_unknown_idFollowUp_findById_should_throw_NotFoundException() {
        //Given
        Long idFollowUp = 0L;

        //Then
        //When
        Assertions.assertThrows(NotFoundException.class, ()->resourceFollowUpRepository.findById(idFollowUp));
    }

    @Test
    public void given_new_resourceFollowUp_save_should_success() {
        //Given
        Resource resource = resourceRepository.findById(1L);
        Member member = new Member();
        member.setIdMember(1L);
        member.setPseudo("emilie");

        ResourceFollowUp resourceFollowUpToSave = new ResourceFollowUp();
        resourceFollowUpToSave.setCreationDate(LocalDate.now());
        resourceFollowUpToSave.setLastModificationDate(LocalDate.now());
        resourceFollowUpToSave.setStatus(StatusEnum.AVOIR);
        resourceFollowUpToSave.setResource(resource);
        resourceFollowUpToSave.setMember(member);

        //Then
        ResourceFollowUp result = resourceFollowUpRepository.save(resourceFollowUpToSave);

        //When
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getIdFollowUp());
        Assertions.assertEquals(resourceFollowUpToSave.getCreationDate(), result.getCreationDate());
        Assertions.assertEquals(resourceFollowUpToSave.getStatus(), result.getStatus());
        Assertions.assertEquals(1L, result.getMember().getIdMember());
        Assertions.assertEquals(1L, result.getResource().getIdResource());
        Assertions.assertEquals(1L, result.getResource().getExternalKey().getId());
    }
}
