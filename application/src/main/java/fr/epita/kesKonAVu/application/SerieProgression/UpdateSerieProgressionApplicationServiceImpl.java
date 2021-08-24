package fr.epita.kesKonAVu.application.SerieProgression;

import fr.epita.kesKonAVu.application.episodeFollowUp.EpisodeFollowUpApplicationService;
import fr.epita.kesKonAVu.domain.SerieProgression.CalculateProgressionService;
import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeFollowUp;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.followUp.FollowUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
@Transactional
public class UpdateSerieProgressionApplicationServiceImpl implements UpdateSerieProgressionApplicationService {
    @Autowired
    CalculateProgressionService calculateProgressionService;

    @Autowired
    FollowUpRepository followUpRepository;
    @Autowired
    EpisodeFollowUpApplicationService episodeFollowUpApplicationService;

    @Override
    public Long updateProgressionSerie (FollowUp in) { // a followUp without its episodeFollowUps

        Long result = 0L;
        //Caculate and set the progression for the followUp
        //And update the followUp
        in.setProgression(calculateProgressionService.calculateProgression(in));
        in.setLastModificationDate(LocalDate.now());
        FollowUp followUpUpdated = followUpRepository.save(in);
        result = followUpUpdated.getIdFollowUp();

        return result;

    }

    @Override
    public Long SaveSerieProgression (FollowUp incomplete) {
        FollowUp retrieved = followUpRepository.findByIdWithAllEpisodeFollowUps(incomplete.getIdFollowUp());
        Set<EpisodeFollowUp> temp = incomplete.getEpisodeFollowUps();
        Set<EpisodeFollowUp> newList = new HashSet<>();

        for (Iterator<EpisodeFollowUp> it1 = temp.iterator(); it1.hasNext(); ) {

            EpisodeFollowUp f1 = it1.next();

            if (f1.getId() != null) { // followUp existant en base

                for (Iterator<EpisodeFollowUp> it2 = retrieved.getEpisodeFollowUps().iterator(); it2.hasNext(); ) {
                    EpisodeFollowUp f2 = it2.next();
                    // Save the status for each actual EpisodeFollowUp
                    if (f2.getId() == f1.getId()) {
                        f2.setStatus(f1.getStatus());
                        f2.setLastModificationDate(LocalDate.now());
                        newList.add(f2);
                    }
                }
            } else {
                // Sinon prévoir création nouvel episodefollowUp
                    f1.setLastModificationDate(LocalDate.now());
                    newList.add(f1);
            }
        }
        // Preparation màj en base du followUp
        retrieved.setEpisodeFollowUps(newList);

        //màj des épidodes followUp
        episodeFollowUpApplicationService.saveSerieprogression(retrieved);
        //màj de la série followUp
        return updateProgressionSerie(retrieved);
    }

}
