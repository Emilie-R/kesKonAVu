package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.followUp.FollowUp;

public interface FollowUpService {

    FollowUp createNewFollowUpFromImdbId(FollowUp resourceFollowUp);

    FollowUp findOne(Long id);

    FollowUp updateFollowUp(FollowUp followUp);

    Long deleteFollowUp(final Long idFollowUp);

}
