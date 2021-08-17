package fr.epita.kesKonAVu.domain.followUp;

import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.user.Member;

import java.util.List;

public interface ResourceFollowUpRepository {

    ResourceFollowUp save(ResourceFollowUp resourceFollowUp);

    ResourceFollowUp findById(Long idFollowUp);

    List<ResourceFollowUp> findByMember(Member member);

    List<ResourceFollowUp> findByResource(Resource resource);

    ResourceFollowUp findByResourceAndMember(Resource resource, Member member);


}
