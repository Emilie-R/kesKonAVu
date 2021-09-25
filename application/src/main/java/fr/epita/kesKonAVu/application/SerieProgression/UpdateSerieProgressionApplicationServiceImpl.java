package fr.epita.kesKonAVu.application.SerieProgression;

import fr.epita.kesKonAVu.domain.SerieProgression.CalculateProgressionService;
import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeFollowUp;
import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeStatusEnum;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.followUp.FollowUpRepository;
import fr.epita.kesKonAVu.domain.resource.Episode;
import fr.epita.kesKonAVu.domain.resource.Serie;
import fr.epita.kesKonAVu.domain.resource.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Autowired
    SerieRepository serieRepository;

    @Override
    public FollowUp updateProgressionSerie (FollowUp in) { // a followUp without its episodeFollowUps

        Long result = 0L;
        //Caculate and set the progression for the followUp
        //And update the followUp
        in.setProgression(calculateProgressionService.calculateProgression(in));
        in.setNumberOfUnseenEpisodes(
                (int) in.getEpisodeFollowUps().stream()
                       .filter(e -> e.getStatus() == EpisodeStatusEnum.AVOIR)
                       .count()
        );
        in.setLastModificationDateTime(LocalDateTime.now());
        FollowUp followUpUpdated = followUpRepository.save(in);
        result = followUpUpdated.getIdFollowUp();

        return followUpUpdated;

    }

    @Override
    public FollowUp saveSerieProgression (FollowUp incomplete) {
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
        String inter = episodeFollowUpApplicationService.saveSerieprogression(retrieved);
        //màj de la série followUp
        return updateProgressionSerie(retrieved);
    }

    @Override
    public FollowUp getEpisodeFollowUpList (Long idFollowUp) {
        FollowUp retrieved = followUpRepository.findByIdWithAllEpisodeFollowUps(idFollowUp);
        FollowUp out = new FollowUp();
        if (retrieved.getEpisodeFollowUps().size() == 0){
            Set<EpisodeFollowUp> setOut = initializeEpisodeFollowUpList(retrieved);
            out.setEpisodeFollowUps(setOut);
        } else {
            out.setEpisodeFollowUps(retrieved.getEpisodeFollowUps());
        }
        out.setIdFollowUp(retrieved.getIdFollowUp());
        return out;
    }

    @Override
    public Set<EpisodeFollowUp> initializeEpisodeFollowUpList (FollowUp followUp) {
        Set<EpisodeFollowUp> setOut = new HashSet<>();
        Serie serie = serieRepository.findByIdWithAllEpisodes(followUp.getResource().getIdResource()); // OK as FollowUp and Resource are in Fecth.EAGER
        //Initialize a episodeFollowUp List
        for (Iterator<Episode> it1 = serie.getEpisodes().iterator(); it1.hasNext(); ) {
            Episode ep = it1.next();
            EpisodeFollowUp ef = new EpisodeFollowUp();
            ef.setEpisode(ep);
            setOut.add(ef);
            ef = null;
        }
        return setOut;
    }

}
