package fr.epita.kesKonAVu.infrastructure.episodeFollowUp;

import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeFollowUp;
import fr.epita.kesKonAVu.domain.resource.Episode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
public class EpisodeFollowUpRepositoryTest {

    @Autowired
    EpisodeFollowUpJpaRepository episodeFollowUpJpaRepository;

    @Test
    public void  createEpisodeFollowUpOKWhenItIsGiven(){
        EpisodeFollowUp e1 = new EpisodeFollowUp();
        e1.setLastModificationDate(LocalDate.now());
        Episode episode = new Episode();
        episode.setTitle("La vie est belle");
        e1.setEpisode(episode);
        EpisodeFollowUp e2 = episodeFollowUpJpaRepository.save(e1);
        Assertions.assertTrue(e2.getId() != null);
        Assertions.assertTrue(e2.getEpisode().getTitle() == "La vie est belle");
    }
    @Test
    public void  getEpisodeFollowUpOKWhenItExists(){
        EpisodeFollowUp e1 = new EpisodeFollowUp();
        e1.setLastModificationDate(LocalDate.now());
        Episode episode = new Episode();
        episode.setTitle("La vie est belle");
        e1.setEpisode(episode);
        EpisodeFollowUp e2 = episodeFollowUpJpaRepository.save(e1);
        EpisodeFollowUp e3 = episodeFollowUpJpaRepository.getById(e2.getId());
        Assertions.assertTrue(e2.getId() == e3.getId());
    }
}
