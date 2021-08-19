package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.followUp.FollowUp;

public interface FollowUpService {

    FollowUp createNewFollowUp(FollowUp resourceFollowUp);

    FollowUp findOne(Long id);

}
