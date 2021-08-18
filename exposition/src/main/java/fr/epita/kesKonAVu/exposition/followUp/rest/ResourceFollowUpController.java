package fr.epita.kesKonAVu.exposition.followUp.rest;

import fr.epita.kesKonAVu.application.followUp.FollowUpService;
import fr.epita.kesKonAVu.application.followUp.ResourceFollowUpService;
import fr.epita.kesKonAVu.application.followUp.SortCriteriaEnum;
import fr.epita.kesKonAVu.application.user.MemberService;
import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.exposition.resource.rest.ResourceDTO;
import fr.epita.kesKonAVu.exposition.resource.rest.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/followup")
public class ResourceFollowUpController {

    @Autowired
    ResourceFollowUpService resourceFollowUpService;

    @Autowired
    FollowUpService followUpService;

    @Autowired
    MemberService memberService;

    ResourceMapper resourceMapper = new ResourceMapper();

    ResourceFollowUpMapper resourceFollowUpMapper = new ResourceFollowUpMapper();

    /**
     *
     * @param idMember identifiant de l'utilisateur
     * @return renvoie une map<Status,liste de suivies de l'utilisateur>
     */
    @GetMapping(value="/{id}", produces={"application/json"})
    public Map<StatusEnum, List<ResourceFollowupDTO>> getMapResourcesMember(@PathVariable("id") Long idMember){

        List<ResourceFollowUp> memberFollowUps = resourceFollowUpService.findByMember(memberService.findOne(idMember));
        Map<StatusEnum,List<ResourceFollowUp>> memberMap =
                resourceFollowUpService.SeparateByStatus(memberFollowUps);
        // Conversion en 2 listes de suivi DTO
        Map<StatusEnum,List<ResourceFollowupDTO>> result = new HashMap<>();
        List<ResourceFollowupDTO> liste = memberMap.get(StatusEnum.VU).stream()
                .map(f -> resourceFollowUpMapper.mapToDto(f))
                .collect(Collectors.toList());
        result.put(StatusEnum.VU,liste);
        liste = null;
        liste = memberMap.get(StatusEnum.AVOIR).stream()
                .map(f -> resourceFollowUpMapper.mapToDto(f))
                .collect(Collectors.toList());
        result.put(StatusEnum.AVOIR,liste);
        return result;
    }

    @PostMapping(value="/sortbydate", produces={"application/json"})
    public List<ResourceDTO> SortResourcesListByDate(@RequestBody List<ResourceFollowUp> enteredFollowUpList){

        List<Resource> sortedResourcesList = resourceFollowUpService.SortByCriteria(enteredFollowUpList, SortCriteriaEnum.DATE);

        //Tranfo. en DTO list
        List<ResourceDTO> sortedResourceDTOList =
                sortedResourcesList.stream().map(e -> resourceMapper.mapToDto(e))
                        .collect(Collectors.toList());
        return sortedResourceDTOList;
    }

    @PostMapping(value="/sortbyrating", produces={"application/json"})
    public List<ResourceDTO> SortResourcesListByRating(@RequestBody List<ResourceFollowUp> enteredFollowUpList){

        List<Resource> sortedResourcesList = resourceFollowUpService.SortByCriteria(enteredFollowUpList, SortCriteriaEnum.RATING);

        //Tranfo. en DTO list
        List<ResourceDTO> sortedResourceDTOList =
                sortedResourcesList.stream().map(e -> resourceMapper.mapToDto(e))
                        .collect(Collectors.toList());

        return sortedResourceDTOList;
    }

    @PostMapping(value = "/create", consumes = {"application/json"})
    public void createNewFollowUp(@Valid @RequestBody ResourceFollowUpDTOLight resourceFollowUpDTOLight) {
        ResourceFollowUp resourceFollowUp = resourceFollowUpMapper.mapToEntity(resourceFollowUpDTOLight);
        followUpService.createNewFollowUp(resourceFollowUp);
    }
}
