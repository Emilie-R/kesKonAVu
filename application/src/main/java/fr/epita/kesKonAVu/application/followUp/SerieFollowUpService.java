package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;
import fr.epita.kesKonAVu.domain.followUp.SerieFollowUp;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.Serie;
import fr.epita.kesKonAVu.domain.user.Member;

import java.util.List;
import java.util.Map;

public interface SerieFollowUpService {
    List<SerieFollowUp> findByMember(Member member);

    List<SerieFollowUp> findByResource(Resource resource);

    Map<StatusEnum,List<SerieFollowUp>> SeparateByStatus(List<SerieFollowUp> serieFollowUpList);

    List<Serie> SortByCriteria(List<SerieFollowUp> serieFollowUpList, SortCriteriaEnum criteria);
}
