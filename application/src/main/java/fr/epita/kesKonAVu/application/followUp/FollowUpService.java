package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;

public interface FollowUpService {

    FollowUp createNewFollowUp(FollowUp resourceFollowUp);

    FollowUp findOne(Long id);


    String updateStatus(Long id, StatusEnum statusEnum);

    String updateRating(Long id, Integer rating);


    Long deleteFollowUp(final Long idFollowUp);

}
