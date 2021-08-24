package fr.epita.kesKonAVu.domain.SerieProgression;

import fr.epita.kesKonAVu.domain.SpringBootAppTest;
import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeFollowUp;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.followUp.FollowUpRepository;
import fr.epita.kesKonAVu.domain.resource.Episode;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import fr.epita.kesKonAVu.domain.resource.Serie;
import fr.epita.kesKonAVu.domain.resource.SerieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest(classes = { SpringBootAppTest.class })
public class EpisodeFollowUpSaveProgressionDomainServiceTest {
    @MockBean
    SerieRepository serieRepository;
    @MockBean
    FollowUpRepository followUpRepository;
    @Autowired
    EpisodeFollowUpSaveProgressionDomainService saveProgressionEpisodes;

    @Test
    public void SaveProgressionOKWhenFollowUpWithEspisodeFollowupsIsGiven(){
        Serie serie = new Serie();
        serie.setTitle("Urgences");
        serie.setResourceType(ResourceTypeEnum.SERIE);
        serie.setExternalKey("1234567");
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
        Mockito.when(serieRepository.save(serie)).thenReturn(serie);
        Serie serie2 = serieRepository.save(serie);
        FollowUp followUp = new FollowUp();
        followUp.setResource(serie);
        Mockito.when(followUpRepository.save(followUp)).thenReturn(followUp);
        FollowUp followUp2 = followUpRepository.save(followUp);
        EpisodeFollowUp e1 = new EpisodeFollowUp();
        e1.setEpisode(episode1);
        EpisodeFollowUp e2 = new EpisodeFollowUp();
        e2.setEpisode(episode2);
        Set<EpisodeFollowUp> list = new HashSet<>();
        list.add(e1);
        list.add(e2);
        followUp2.setEpisodeFollowUps(list);
        Assertions.assertEquals("OK",saveProgressionEpisodes.saveSerieprogression(followUp2.getEpisodeFollowUps()));
    }
}