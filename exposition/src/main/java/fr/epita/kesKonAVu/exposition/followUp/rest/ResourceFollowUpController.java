package fr.epita.kesKonAVu.exposition.followUp.rest;

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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/followup")
public class ResourceFollowUpController {

    @Autowired
    ResourceFollowUpService resourceFollowUpService;

    ResourceMapper resourceMapper = new ResourceMapper();

    ResourceFollowUpMapper resourceFollowUpMapper = new ResourceFollowUpMapper();


}
