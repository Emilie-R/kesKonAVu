package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;

public interface FollowUpService {

    FollowUp createNewFollowUp(FollowUp resourceFollowUp);

    FollowUp findOne(Long id);

    Long deleteFollowUp(final Long idFollowUp);
}
