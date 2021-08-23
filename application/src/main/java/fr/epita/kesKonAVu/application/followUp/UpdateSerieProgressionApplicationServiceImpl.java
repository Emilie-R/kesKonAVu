package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.followUp.FollowUpDomainService;
import fr.epita.kesKonAVu.domain.followUp.FollowUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
public class UpdateSerieProgressionApplicationServiceImpl implements UpdateSerieProgressionApplicationService {
    @Autowired
    FollowUpDomainService followUpDomainService;

    @Autowired
    FollowUpRepository followUpRepository;

    @Override
    public Long updateProgressionSerie (FollowUp in) { // a followUp without its episodeFollowUps

        Long result = 0L;
        //Caculate and set the progression for the followUp
        //And update the followUp
        in.setProgression(followUpDomainService.calculateProgressionForASerie(in));
        in.setLastModificationDate(LocalDate.now());
        FollowUp followUpUpdated = followUpRepository.save(in);
        result = followUpUpdated.getIdFollowUp();

        return result;

    }
}
