package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeFollowUpRepository;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.followUp.FollowUpDomainService;
import fr.epita.kesKonAVu.domain.followUp.FollowUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FollowUpProgressionApplicationServiceImpl implements FollowUpProgressionApplicationService {
    @Autowired
    FollowUpDomainService followUpDomainService;

    @Autowired
    EpisodeFollowUpRepository episodeFollowUpRepository;

    @Autowired
    FollowUpRepository followUpRepository;

    @Override
    public Long updateProgressionSerie (FollowUp in) { // a followUp with its episodeFollowUps

        Long result = 0L;
        //Caculate and set the progression for the followUp(Serie)
        //And update the followUp
        in.setProgression(followUpDomainService.calculateProgressionForASerie(in));
        in.setLastModificationDate(LocalDate.now());
        FollowUp followUpUpdated = followUpRepository.save(in);
        result = followUpUpdated.getIdFollowUp();
        //Update the status for each episodeFolluwUp
        in.getEpisodeFollowUps().stream().forEach(
                e -> {
                    e.setLastModificationDate(LocalDate.now());
                    episodeFollowUpRepository.save(e);
                }
        );


        return result;

    }
}
