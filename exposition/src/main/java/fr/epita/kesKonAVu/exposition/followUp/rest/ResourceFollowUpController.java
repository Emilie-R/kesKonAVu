package fr.epita.kesKonAVu.exposition.followUp.rest;

import fr.epita.kesKonAVu.application.followUp.FollowUpService;
import fr.epita.kesKonAVu.application.followUp.ResourceFollowUpService;
import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/followup")
public class ResourceFollowUpController {

    @Autowired
    ResourceFollowUpService resourceFollowUpService;

    @Autowired
    FollowUpService followUpService;

    @Autowired
    ResourceFollowUpMapper resourceFollowUpMapper;

    @GetMapping(value="/{id}", produces={"application/json"})
    public ResourceFollowupDTO getResourceFollowUp(@PathVariable("id") Long id){
        ResourceFollowUp in = resourceFollowUpService.findOne(id);
        return resourceFollowUpMapper.mapToDto(in);
    }

    @PostMapping(value = "/create", consumes = {"application/json"})
    public ResourceFollowupDTO createNewFollowUp(@Valid @RequestBody ResourceFollowUpDTOLight resourceFollowUpDTOLight) {
        ResourceFollowUp resourceFollowUp = resourceFollowUpMapper.mapToEntity(resourceFollowUpDTOLight);
        ResourceFollowUp resourceFollowUpSaved = followUpService.createNewFollowUp(resourceFollowUp);
        return resourceFollowUpMapper.mapToDto(resourceFollowUpSaved);
    }
}
