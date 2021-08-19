package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.followUp.FollowUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ResourceFollowUpServiceImpl implements ResourceFollowUpService{

    @Autowired
    FollowUpRepository followUpRepository;

    @Override
    public FollowUp createResourceFollowUp (FollowUp resourceFollowUp) {
        resourceFollowUp.setCreationDate(LocalDate.now());
        resourceFollowUp.setLastModificationDate(LocalDate.now());
        // Return created resourceFollowUp in DataBase
        return followUpRepository.save(resourceFollowUp);
    }

    @Override
    public FollowUp updateResourceFollowUp (FollowUp resourceFollowUp) {
        // TODO - Don't needed actually
        return null;
    }

    @Override
    public FollowUp findOne (Long id) {
        if (followUpRepository.findById(id).isPresent())
        {
            return followUpRepository.findById(id).get();
        } else {
            throw new NotFoundException("resourceFollowUp non trouvé en BDD");
        }
    }
}
