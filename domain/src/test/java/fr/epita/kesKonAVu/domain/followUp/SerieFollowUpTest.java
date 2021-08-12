package fr.epita.kesKonAVu.domain.followUp;

import fr.epita.kesKonAVu.domain.common.ResourceTypeException;
import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeFollowUp;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.Serie;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import fr.epita.kesKonAVu.domain.SpringBootAppTest;
import java.util.HashSet;
import java.util.Set;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = { SpringBootAppTest.class })
public class SerieFollowUpTest {

    @Test
    public void shouldCalculateTheRightProgressionWhenSerieFollowUpIsPassed(){
        SerieFollowUp serieFollowUp = new SerieFollowUp();

        Set<EpisodeFollowUp> setEpisodes = new HashSet<>();

        EpisodeFollowUp episodeFollowUp1 = new EpisodeFollowUp();
        EpisodeFollowUp episodeFollowUp2 = new EpisodeFollowUp();
        episodeFollowUp1.setStatus(StatusEnum.VU);
        episodeFollowUp2.setStatus(StatusEnum.AVOIR);
        setEpisodes.add(episodeFollowUp1);
        setEpisodes.add(episodeFollowUp2);
        serieFollowUp.setEpisodeFollowUps(setEpisodes);
        Serie serie = new Serie();
        serie.setNumberOfEpisodes(2);
        serieFollowUp.setResource(serie);
        serieFollowUp.CalculateProgression();
        Assertions.assertEquals(50F, serieFollowUp.getProgression());

    }
    @Test
    public void shouldNotCalculateAProgressionWhenMovieFollowUpIsPassed(){
        SerieFollowUp serieFollowUp = new SerieFollowUp();

        Set<EpisodeFollowUp> setEpisodes = new HashSet<>();

        EpisodeFollowUp episodeFollowUp1 = new EpisodeFollowUp();
        EpisodeFollowUp episodeFollowUp2 = new EpisodeFollowUp();
        episodeFollowUp1.setStatus(StatusEnum.VU);
        episodeFollowUp2.setStatus(StatusEnum.AVOIR);
        setEpisodes.add(episodeFollowUp1);
        setEpisodes.add(episodeFollowUp2);
        serieFollowUp.setEpisodeFollowUps(setEpisodes);
        Resource serie = new Resource();// It's a movie follow-up
        serieFollowUp.setResource(serie);
        Assertions.assertThrows(ResourceTypeException.class, serieFollowUp::CalculateProgression);

    }
    @Test
    public void shouldSendZeroIfNoEpisodeForTheSerie(){
        SerieFollowUp serieFollowUp = new SerieFollowUp();

        Set<EpisodeFollowUp> setEpisodes = new HashSet<>();

        EpisodeFollowUp episodeFollowUp1 = new EpisodeFollowUp();
        EpisodeFollowUp episodeFollowUp2 = new EpisodeFollowUp();
        episodeFollowUp1.setStatus(StatusEnum.VU);
        episodeFollowUp2.setStatus(StatusEnum.AVOIR);
        setEpisodes.add(episodeFollowUp1);
        setEpisodes.add(episodeFollowUp2);
        serieFollowUp.setEpisodeFollowUps(setEpisodes);
        Serie serie = new Serie(); // Number of episodes is not setted
        serieFollowUp.setResource(serie);
        serieFollowUp.CalculateProgression();
        Assertions.assertEquals(0F,serieFollowUp.getProgression());

    }
}
