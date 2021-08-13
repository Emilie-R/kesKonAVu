package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;
import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUpRepository;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.user.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourceFollowUpServiceImpl implements ResourceFollowUpService{
    @Autowired
    ResourceFollowUpRepository resourceFollowUpRepository;

    @Override
    public List<ResourceFollowUp> findByMember (Member member) {
        return resourceFollowUpRepository.findByMember(member);
    }

    @Override
    public List<ResourceFollowUp> findByResource (Resource resource) {
        return resourceFollowUpRepository.findByResource(resource);
    }

    /**
     *
     * @param resourceFollowUpList : List<ResourceFollowUp> = liste des suivis films suivis par l'utilisateur
     * @return renvoie 2 listes de films : la première regroupe les
     * films déjà vus; la seconde liste contient les films 'pas vu'.
     */
    @Override
    public List<List<ResourceFollowUp>> SeparateByStatus (List<ResourceFollowUp> resourceFollowUpList) {

        List<ResourceFollowUp> listOfSeenMovies = resourceFollowUpList.stream()
                .filter(r -> r.getStatus() == StatusEnum.VU)
                .collect(Collectors.toList());
        List<ResourceFollowUp> listOfUnseenMovies = resourceFollowUpList.stream()
                .filter(r -> r.getStatus() == StatusEnum.AVOIR)
                .collect(Collectors.toList());
        List<List<ResourceFollowUp>> result = new ArrayList<>(new ArrayList<>());
        result.add(listOfSeenMovies);
        result.add(listOfUnseenMovies);
        return result;
    }

    /**
     *
     * @param resourceFollowUpList : List<ResourceFollowUp> = Liste des suivis de films en entrée
     * @param criteria tri possible par date, note, status (vu/pas vu), année de sortie
     * @return renvoie la même liste triée en fonction du critère demandée
     */
    @Override
    public List<Resource> SortByCriteria (List<ResourceFollowUp> resourceFollowUpList, SortCriteriaEnum criteria) {

        switch (criteria) {
            case STATUS:
                Collections.sort(resourceFollowUpList, Comparator.comparing(ResourceFollowUp::getStatus));
                break;
            case RATING:
                Collections.sort(resourceFollowUpList, Comparator.comparing(ResourceFollowUp::getNote));
                break;
            case DATE:
                Collections.sort(resourceFollowUpList, Comparator.comparing(ResourceFollowUp::getLastModificationDate));
                break;
            default:
                break;
        }
        List<Resource> result = resourceFollowUpList.stream()
                .map(r -> r.getResource()).collect(Collectors.toList());
        return result;
    }
}
