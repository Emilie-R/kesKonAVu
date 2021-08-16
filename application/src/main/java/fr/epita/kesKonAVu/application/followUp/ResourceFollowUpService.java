package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.user.Member;

import java.util.List;
import java.util.Map;

public interface ResourceFollowUpService {
    List<ResourceFollowUp> findByMember(Member member);

    List<ResourceFollowUp> findByResource(Resource resource);

    Map<StatusEnum,List<ResourceFollowUp>> SeparateByStatus(List<ResourceFollowUp> resourceFollowUpList);

    List<Resource> SortByCriteria(List<ResourceFollowUp> resourceFollowUpList, SortCriteriaEnum criteria);
}
