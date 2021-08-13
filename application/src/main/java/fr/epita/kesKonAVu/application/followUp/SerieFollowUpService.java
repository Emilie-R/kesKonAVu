package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.followUp.SerieFollowUp;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.user.Member;

import java.util.List;

public interface SerieFollowUpService {
    List<SerieFollowUp> findByMember(Member member);

    List<SerieFollowUp> findByResource(Resource resource);
}
