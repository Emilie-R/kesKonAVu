package fr.epita.kesKonAVu.application.episodeFollowUp;

import fr.epita.kesKonAVu.domain.common.BusinessException;
import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeFollowUpDomainService;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EpisodeFollowUpApplicationServiceImpl implements EpisodeFollowUpApplicationService {
    @Autowired
    EpisodeFollowUpDomainService episodeFollowUpDomainService;

    @Override
    public String saveSerieprogression (FollowUp in) {
        if (in == null || in.getEpisodeFollowUps() == null) {
            throw new BusinessException("Il n'y a pas d'épisodeFollowUp à mettre à jour !");
        }
        episodeFollowUpDomainService.saveSerieprogression(in.getEpisodeFollowUps());

        return "OK";
    }
}
