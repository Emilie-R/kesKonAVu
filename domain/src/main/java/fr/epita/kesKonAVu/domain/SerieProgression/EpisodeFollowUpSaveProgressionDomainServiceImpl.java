package fr.epita.kesKonAVu.domain.SerieProgression;

import fr.epita.kesKonAVu.domain.common.BusinessException;
import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeFollowUp;
import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeFollowUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Set;

@Service
@Transactional
public class EpisodeFollowUpSaveProgressionDomainServiceImpl implements EpisodeFollowUpSaveProgressionDomainService {
    @Autowired
    EpisodeFollowUpRepository episodeFollowUpRepository;

    @Override
    public String saveSerieprogression (Set<EpisodeFollowUp> listIn) {
        String retour = "KO";
        try {
            listIn.stream()
                    .forEach(e -> {e.setLastModificationDate(LocalDate.now());
                        episodeFollowUpRepository.save(e);});
        } catch (Exception e) {
            throw new BusinessException("Echec mise à jour des épisodes : " + e.getMessage());
        }
        retour = "OK";
        return retour;
    }
}
