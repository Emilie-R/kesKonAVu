package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.followUp.FollowUp;

public interface FollowUpService {

    FollowUp createNewFollowUp(FollowUp resourceFollowUp);

    FollowUp findOne(Long id);


    String updateFollowUp(FollowUp followUp);

//    String updateRating(Long id, Integer rating);


    Long deleteFollowUp(final Long idFollowUp);

    Long SaveSerieProgression(FollowUp in);

}
