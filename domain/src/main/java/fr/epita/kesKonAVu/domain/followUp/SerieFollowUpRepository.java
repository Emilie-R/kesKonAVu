package fr.epita.kesKonAVu.domain.followUp;

import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.user.Member;

import java.util.List;

public interface SerieFollowUpRepository {
    List<SerieFollowUp> findByMember(Member member);

    List<SerieFollowUp> findByResource(Resource resource);

}
