package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;
import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUpRepository;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.user.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
     * @return renvoie une map<Status.Enum,List<ResourceFollowUp
     * : la première paire (clé,valeur) regroupe les
     * films déjà vus; la seconde paire contient les films 'pas vu'.
     */
    @Override
    public Map<StatusEnum,List<ResourceFollowUp>> SeparateByStatus (List<ResourceFollowUp> resourceFollowUpList) {

        List<ResourceFollowUp> listOfSeenMovies = resourceFollowUpList.stream()
                .filter(r -> r.getStatus() == StatusEnum.VU)
                .collect(Collectors.toList());
        List<ResourceFollowUp> listOfUnseenMovies = resourceFollowUpList.stream()
                .filter(r -> r.getStatus() == StatusEnum.AVOIR)
                .collect(Collectors.toList());
        List<List<ResourceFollowUp>> result = new ArrayList<>(new ArrayList<>());
        result.add(listOfSeenMovies);
        result.add(listOfUnseenMovies);

        Map<StatusEnum,List<ResourceFollowUp>> mapResult = new HashMap<>();
        mapResult.put(StatusEnum.VU,listOfSeenMovies);
        mapResult.put(StatusEnum.AVOIR,listOfUnseenMovies);

        return mapResult;
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
                Collections.sort(resourceFollowUpList, Comparator.comparing(ResourceFollowUp::getNote).reversed());
                break;

            default:
                // tri par date de dernière mise à jour par défaut
                Collections.sort(resourceFollowUpList, Comparator.comparing(ResourceFollowUp::getLastModificationDate).reversed());
                break;
        }
        List<Resource> result = resourceFollowUpList.stream()
                .map(r -> r.getResource()).collect(Collectors.toList());
        return result;
    }
}
