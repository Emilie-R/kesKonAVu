package fr.epita.kesKonAVu.domain.followUp;

import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.user.Member;

import java.util.Optional;

public interface FollowUpRepository {

    FollowUp save(FollowUp followUp);

    Optional<FollowUp> findById(Long idFollowUp);

    FollowUp findByResourceAndMember(Resource resource, Member member);


}
