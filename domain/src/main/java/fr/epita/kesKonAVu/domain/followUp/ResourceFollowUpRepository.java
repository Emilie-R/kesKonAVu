package fr.epita.kesKonAVu.domain.followUp;

import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.user.Member;

import java.util.List;

public interface ResourceFollowUpRepository {

    ResourceFollowUp save(ResourceFollowUp resourceFollowUp);

    List<ResourceFollowUp> findByMember(Member member);

    List<ResourceFollowUp> findByResource(Resource resource);


}
