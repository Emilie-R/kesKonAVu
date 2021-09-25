package fr.epita.kesKonAVu.application.SerieProgression;

import fr.epita.kesKonAVu.SpringBootAppTest;
import fr.epita.kesKonAVu.domain.catalogueOmdb.CatalogueRepository;
import fr.epita.kesKonAVu.domain.common.BusinessException;
import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeStatusEnum;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.followUp.FollowUpRepository;
import fr.epita.kesKonAVu.domain.resource.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest(classes = {SpringBootAppTest.class})
public class UpdateSerieProgressionApplicationServiceTest {

    @Autowired
    UpdateSerieProgressionApplicationService updateSerieProgressionApplicationService;

    @Autowired
    FollowUpRepository followUpRepository;

    @Autowired
    SerieRepository serieRepository;

    @MockBean
    CatalogueRepository catalogueRepository;

    @Test
    public void UpdateProgressionOKWhenFollowUpIsGiven(){
        FollowUp followUp = followUpRepository.findByIdWithAllEpisodeFollowUps(3L);

        FollowUp FollowUpUpdated = updateSerieProgressionApplicationService.updateProgressionSerie(followUp);

        Assertions.assertEquals(followUp.getIdFollowUp() ,FollowUpUpdated.getIdFollowUp());
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

        FollowUp fpr = updateSerieProgressionApplicationService.saveSerieProgression(f1);

        FollowUp f2 = followUpRepository.findByIdWithAllEpisodeFollowUps(fpr.getIdFollowUp());

        Long filteredCount = f2.getEpisodeFollowUps().stream()
                .filter(e -> e.getStatus() == EpisodeStatusEnum.VU).count();

        Assertions.assertEquals(5, filteredCount);
        Assertions.assertEquals(100, f2.getProgression());

    }

    @Test
    public void GetEpisodeLFollowUpListOKWhenGivenAFollowUpWithoutEpisodeFollowUps(){
        Serie serie = new Serie();
        serie.setTitle("Urgences");
        serie.setResourceType(ResourceTypeEnum.SERIE);
        serie.setImdbId("1234567");
        serie.setNumberOfSeasons(2);

        Episode episode1 = new Episode();
        episode1.setSeasonNumber(1);
        episode1.setNumber(1);
        episode1.setTitle("E1");
        Episode episode2 = new Episode();
        episode2.setSeasonNumber(1);
        episode2.setNumber(2);
        episode2.setTitle("E2");
        Episode episode3 = new Episode();
        episode3.setSeasonNumber(2);
        episode3.setNumber(3);
        episode3.setTitle("E3");
        Set<Episode> episodes = new HashSet<>();
        episodes.add(episode1);
        episodes.add(episode2);
        episodes.add(episode3);
        serie.setEpisodes(episodes);
        Serie serie2 = serieRepository.save(serie);
        FollowUp followUp = new FollowUp();
        followUp.setResource(serie2);
        FollowUp followUp2 = followUpRepository.save(followUp);
        FollowUp followUp3 = updateSerieProgressionApplicationService.getEpisodeFollowUpList(followUp2.getIdFollowUp());
        Assertions.assertEquals(3,followUp3.getEpisodeFollowUps().size());
    }
}
