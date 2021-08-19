package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.followUp.FollowUp;

public interface ResourceFollowUpService {

    FollowUp createResourceFollowUp(FollowUp resourceFollowUp);

    FollowUp updateResourceFollowUp(FollowUp resourceFollowUp);

    FollowUp findOne(Long id);

}
