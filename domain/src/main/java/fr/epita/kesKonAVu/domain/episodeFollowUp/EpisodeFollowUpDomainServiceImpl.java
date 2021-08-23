package fr.epita.kesKonAVu.domain.episodeFollowUp;

import fr.epita.kesKonAVu.domain.common.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Set;

@Service
@Transactional
public class EpisodeFollowUpDomainServiceImpl implements EpisodeFollowUpDomainService{
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
            throw new BusinessException("Echec mise à jour des épisodes : " + listIn.toString());
        }
        retour = "OK";
        return retour;
    }
}
