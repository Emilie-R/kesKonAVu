package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;

public interface FollowUpService {

    ResourceFollowUp createNewFollowUp(final ResourceFollowUp resourceFollowUp);

    Long deleteFollowUp(final Long idFollowUp);
}
