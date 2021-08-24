package fr.epita.kesKonAVu.application.SerieProgression;

import fr.epita.kesKonAVu.SpringBootAppTest;
import fr.epita.kesKonAVu.domain.common.BusinessException;
import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeStatusEnum;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.followUp.FollowUpRepository;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {SpringBootAppTest.class})
public class UpdateSerieProgressionApplicationServiceTest {

    @Autowired
    UpdateSerieProgressionApplicationService updateSerieProgressionApplicationService;

    @Autowired
    FollowUpRepository followUpRepository;

    @Test
    public void UpdateProgressionOKWhenFollowUpIsGiven(){
        FollowUp followUp = followUpRepository.findById(3L).get();

        Long idFollowUpUpdated = updateSerieProgressionApplicationService.updateProgressionSerie(followUp);

        Assertions.assertEquals(followUp.getIdFollowUp() ,idFollowUpUpdated);
        Assertions.assertEquals(100L,followUp.getProgression());


    }

    @Test
    public void CalculateProgressionKOWhenFollowUpWithoutEpisodeFollowUpIsGiven(){
        FollowUp followUp = new FollowUp();
        Assertions.assertThrows(BusinessException.class,() -> updateSerieProgressionApplicationService.updateProgressionSerie(followUp));
    }
    @Test
    public void CalculateProgressionKOWhenFollowUpWForAMovieIsGiven(){
        FollowUp followUp = new FollowUp();
        Resource resource = new Resource();
        resource.setResourceType(ResourceTypeEnum.MOVIE);
        followUp.setResource(resource);
        Assertions.assertThrows(BusinessException.class,() -> updateSerieProgressionApplicationService.updateProgressionSerie(followUp));
    }

    @Test
    public void SaveSerieProgressionOK ( ) {
        FollowUp f1 = followUpRepository.findByIdWithAllEpisodeFollowUps(3L);

        f1.getEpisodeFollowUps().stream()
                .forEach(e -> e.setStatus(EpisodeStatusEnum.VU));

        Long id = updateSerieProgressionApplicationService.SaveSerieProgression(f1);

        FollowUp f2 = followUpRepository.findById(id).get();

        Long filteredCount = f2.getEpisodeFollowUps().stream()
                .filter(e -> e.getStatus() == EpisodeStatusEnum.VU).count();

        Assertions.assertEquals(5, filteredCount);
        Assertions.assertEquals(100, f2.getProgression());

    }
}
