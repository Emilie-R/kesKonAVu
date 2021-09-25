package fr.epita.kesKonAVu.application.SerieProgression;

import fr.epita.kesKonAVu.SpringBootAppTest;
import fr.epita.kesKonAVu.domain.SerieProgression.EpisodeFollowUpSaveProgressionDomainService;
import fr.epita.kesKonAVu.domain.common.BusinessException;
import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeFollowUp;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = { SpringBootAppTest.class })
public class EpisodeFollowUpApplicationServiceTest {
    @Autowired
    EpisodeFollowUpApplicationService episodeApplicationService;

    @MockBean
    EpisodeFollowUpSaveProgressionDomainService episodeDomainService;

    @Test
    public void UpdateKOWhenFollowUpWithoutItsEpisodeFollowUpsIsGiven(){
        FollowUp followUp = new FollowUp();
        followUp.setEpisodeFollowUps(null);

        Assertions.assertThrows(BusinessException.class,
                ()->episodeApplicationService.saveSerieprogression(followUp));
    }

    @Test
    public void UpdateOKWhenFollowUpMockIsGiven(){
        FollowUp followUpMock = new FollowUp();
        EpisodeFollowUp e1 = new EpisodeFollowUp();
        followUpMock.setEpisodeFollowUps(new HashSet<>());
        followUpMock.addEpisodeFollowup(e1);
        Mockito.when(episodeDomainService.saveSerieprogression(any())).thenReturn("");
        Assertions.assertEquals("OK",episodeApplicationService.saveSerieprogression(followUpMock));
    }
}
