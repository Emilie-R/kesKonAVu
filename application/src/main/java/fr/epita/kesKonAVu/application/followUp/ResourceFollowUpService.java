package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;
import fr.epita.kesKonAVu.domain.resource.Resource;

public interface ResourceFollowUpService {

    ResourceFollowUp createResourceFollowUp(ResourceFollowUp resourceFollowUp);

    ResourceFollowUp updateResourceFollowUp(ResourceFollowUp resourceFollowUp);

    ResourceFollowUp findOne(Long id);

}
