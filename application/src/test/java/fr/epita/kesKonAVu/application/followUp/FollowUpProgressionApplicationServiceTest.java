package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.SpringBootAppTest;
import fr.epita.kesKonAVu.domain.common.BusinessException;
import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeFollowUp;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.followUp.FollowUpRepository;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.ResourceRepository;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import fr.epita.kesKonAVu.infrastructure.episodeFollowUp.EpisodeFollowUpJpaRepository;
import fr.epita.kesKonAVu.infrastructure.followUp.FollowUpJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest(classes = {SpringBootAppTest.class})
public class FollowUpProgressionApplicationServiceTest {

    @Autowired
    FollowUpProgressionApplicationService followUpProgressionApplicationService;

    @Autowired
    FollowUpRepository followUpRepository;

    @Autowired
    EpisodeFollowUpJpaRepository episodeFollowUpJpaRepository;

    @Autowired
    FollowUpJpaRepository followUpJpaRepository;

    @Test
    public void UpdateProgressionOKWhenFollowUpIsGiven(){
        FollowUp followUp = followUpRepository.findById(1L).get();

//        Mockito.when(episodeFollowUpJpaRepository.save(epf1)).thenReturn(epf1);
//        Mockito.when(episodeFollowUpJpaRepository.save(epf2)).thenReturn(epf2);
//        Mockito.when(episodeFollowUpJpaRepository.save(epf3)).thenReturn(epf3);
//        Mockito.when(followUpJpaRepository.save(followUp)).thenReturn(followUp);
        Long idFollowUpUpdated = followUpProgressionApplicationService.updateProgressionSerie(followUp);

        Assertions.assertEquals(33F,followUp.getProgression());
        Assertions.assertNull(followUp.getIdFollowUp());

    }

    @Test
    public void CalculateProgressionKOWhenFollowUpWithoutEpisodeFollowUpIsGiven(){
        FollowUp followUp = new FollowUp();
        Assertions.assertThrows(BusinessException.class,() -> followUpProgressionApplicationService.updateProgressionSerie(followUp));
    }
    @Test
    public void CalculateProgressionKOWhenFollowUpWForAMovieIsGiven(){
        FollowUp followUp = new FollowUp();
        Resource resource = new Resource();
        resource.setResourceType(ResourceTypeEnum.MOVIE);
        followUp.setResource(resource);
        Assertions.assertThrows(BusinessException.class,() -> followUpProgressionApplicationService.updateProgressionSerie(followUp));
    }

}
