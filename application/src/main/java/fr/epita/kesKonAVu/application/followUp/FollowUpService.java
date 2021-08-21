package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;

public interface FollowUpService {

    FollowUp createNewFollowUp(FollowUp resourceFollowUp);

    FollowUp findOne(Long id);

<<<<<<< HEAD
    String updateStatus(Long id, StatusEnum statusEnum);

    String updateRating(Long id, Integer rating);

=======
    Long deleteFollowUp(final Long idFollowUp);
>>>>>>> 78634657e037e64b5c48e7b5be311cca339f4b45
}
