package fr.epita.kesKonAVu.domain.SerieProgression;

import fr.epita.kesKonAVu.SpringBootAppTest;
import fr.epita.kesKonAVu.domain.common.BusinessException;
import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeFollowUp;
import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeStatusEnum;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import fr.epita.kesKonAVu.domain.resource.Serie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;


@SpringBootTest(classes = { SpringBootAppTest.class })
public class CalculateProgressionServiceTest {

    @Autowired
    CalculateProgressionService fds;

    @Test
    public void shouldCalculateTheRightProgressionWhenSerieFollowUpIsPassed(){
        FollowUp serieFollowUp = new FollowUp();

        Set<EpisodeFollowUp> setEpisodes = new HashSet<>();

        EpisodeFollowUp episodeFollowUp1 = new EpisodeFollowUp
                ();
        EpisodeFollowUp episodeFollowUp2 = new EpisodeFollowUp();
        episodeFollowUp1.setStatus(EpisodeStatusEnum.VU);
        episodeFollowUp2.setStatus(EpisodeStatusEnum.AVOIR);
        setEpisodes.add(episodeFollowUp1);
        setEpisodes.add(episodeFollowUp2);
        serieFollowUp.setEpisodeFollowUps(setEpisodes);
        Serie serie = new Serie();
        serie.setNumberOfEpisodes(2);
        serieFollowUp.setResource(serie);
        Assertions.assertEquals(50F, fds.calculateProgression(serieFollowUp));

    }
    @Test
    public void shouldNotCalculateAProgressionWhenMovieFollowUpIsPassed(){
        FollowUp serieFollowUp = new FollowUp();

        Set<EpisodeFollowUp> setEpisodes = new HashSet<>();

        EpisodeFollowUp episodeFollowUp1 = new EpisodeFollowUp();
        EpisodeFollowUp episodeFollowUp2 = new EpisodeFollowUp();
        episodeFollowUp1.setStatus(EpisodeStatusEnum.VU);
        episodeFollowUp2.setStatus(EpisodeStatusEnum.AVOIR);
        setEpisodes.add(episodeFollowUp1);
        setEpisodes.add(episodeFollowUp2);
        serieFollowUp.setEpisodeFollowUps(setEpisodes);
        Serie serie = new Serie();
        serie.setResourceType(ResourceTypeEnum.MOVIE);// It's a Movie follow-up
        serieFollowUp.setResource(serie);
        final FollowUp payload = serieFollowUp;
        Assertions.assertThrows(BusinessException.class, () -> fds.calculateProgression(payload));

    }

}
