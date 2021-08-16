package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;
import fr.epita.kesKonAVu.domain.followUp.SerieFollowUp;
import fr.epita.kesKonAVu.domain.followUp.SerieFollowUpRepository;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.Serie;
import fr.epita.kesKonAVu.domain.user.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SerieFollowUpServiceImpl implements SerieFollowUpService{
    @Autowired
    SerieFollowUpRepository serieFollowUpRepository;

    @Override
    public List<SerieFollowUp> findByMember (Member member) {
        return serieFollowUpRepository.findByMember(member);
    }

    @Override
    public List<SerieFollowUp> findByResource (Resource resource) {
        return serieFollowUpRepository.findByResource(resource);
    }

    @Override
    public Map<StatusEnum, List<SerieFollowUp>> SeparateByStatus (List<SerieFollowUp> serieFollowUpList) {
        List<SerieFollowUp> listOfSeenSeries = serieFollowUpList.stream()
                .filter(r -> r.getStatus() == StatusEnum.VU)
                .collect(Collectors.toList());
        List<SerieFollowUp> listOfUnseenSeries = serieFollowUpList.stream()
                .filter(r -> r.getStatus() == StatusEnum.AVOIR)
                .collect(Collectors.toList());
        List<List<SerieFollowUp>> result = new ArrayList<>(new ArrayList<>());
        result.add(listOfSeenSeries);
        result.add(listOfUnseenSeries);

        Map<StatusEnum,List<SerieFollowUp>> mapResult = new HashMap<>();
        mapResult.put(StatusEnum.VU,listOfSeenSeries);
        mapResult.put(StatusEnum.AVOIR,listOfUnseenSeries);

        return mapResult;
    }

    @Override
    public List<Serie> SortByCriteria (List<SerieFollowUp> serieFollowUpList, SortCriteriaEnum criteria) {
        switch (criteria) {
            case STATUS:
                Collections.sort(serieFollowUpList, Comparator.comparing(SerieFollowUp::getStatus));
                break;
            case RATING:
                Collections.sort(serieFollowUpList, Comparator.comparing(SerieFollowUp::getNote).reversed());
                break;
            default:
                // tri par date de dernière mise à jour par défaut
                Collections.sort(serieFollowUpList, Comparator.comparing(SerieFollowUp::getLastModificationDate).reversed());
                break;
        }
        List<Serie> result = serieFollowUpList.stream()
                .map(r -> (Serie)r.getResource()).collect(Collectors.toList());
        return result;
    }
}
