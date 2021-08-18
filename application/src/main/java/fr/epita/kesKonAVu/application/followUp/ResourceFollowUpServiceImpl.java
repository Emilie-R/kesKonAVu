package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;
import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ResourceFollowUpServiceImpl implements ResourceFollowUpService{

    @Autowired
    ResourceFollowUpRepository resourceFollowUpRepository;

    @Override
    public ResourceFollowUp createResourceFollowUp (ResourceFollowUp resourceFollowUp) {
        resourceFollowUp.setCreationDate(LocalDate.now());
        resourceFollowUp.setLastModificationDate(LocalDate.now());
        // Return created resourceFollowUp in DataBase
        return resourceFollowUpRepository.save(resourceFollowUp);
    }

    @Override
    public ResourceFollowUp updateResourceFollowUp (ResourceFollowUp resourceFollowUp) {
        // TODO - Don't needed actually
        return null;
    }

    @Override
    public ResourceFollowUp findOne (Long id) {
        if (resourceFollowUpRepository.findById(id).isPresent())
        {
            return resourceFollowUpRepository.findById(id).get();
        } else {
            throw new NotFoundException("resourceFollowUp non trouvé en BDD");
        }
    }
}
