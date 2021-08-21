package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.SpringBootAppTest;
import fr.epita.kesKonAVu.domain.common.BusinessException;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.followUp.FollowUpRepository;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import fr.epita.kesKonAVu.infrastructure.episodeFollowUp.EpisodeFollowUpJpaRepository;
import fr.epita.kesKonAVu.infrastructure.followUp.FollowUpJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {SpringBootAppTest.class})
public class FollowUpProgressionApplicationServiceIntegrationTest {

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
        FollowUp followUp = followUpRepository.findById(3L).get();

        Long idFollowUpUpdated = followUpProgressionApplicationService.updateProgressionSerie(followUp);

        Assertions.assertEquals(followUp.getIdFollowUp() ,idFollowUpUpdated);
        Assertions.assertEquals(60L,followUp.getProgression());


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
