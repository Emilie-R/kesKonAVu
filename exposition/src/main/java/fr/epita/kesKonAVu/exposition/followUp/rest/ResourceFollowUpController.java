package fr.epita.kesKonAVu.exposition.followUp.rest;

import fr.epita.kesKonAVu.application.followUp.ResourceFollowUpService;
import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;
import fr.epita.kesKonAVu.exposition.resource.rest.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/followup")
public class ResourceFollowUpController {

    @Autowired
    ResourceFollowUpService resourceFollowUpService;

    ResourceFollowUpMapper resourceFollowUpMapper = new ResourceFollowUpMapper();

    @GetMapping(value="/{id}", produces={"application/json"})
    public ResourceFollowupDTO getResourceFollowUp(@PathVariable("id") Long id){
        ResourceFollowUp in = resourceFollowUpService.findOne(id);
        return resourceFollowUpMapper.mapToDto(in);
    }

    @PostMapping(value = "/create", produces={"application/json"}, consumes = {"application/json"})
    public ResourceFollowupDTO createFollowUp(@RequestBody ResourceFollowupDTO in){
        ResourceFollowUp resourceFollowUpToCreate = resourceFollowUpMapper.mapToEntity(in);
        ResourceFollowUp resourceFollowupCreated = resourceFollowUpService.createResourceFollowUp(resourceFollowUpToCreate);
        ResourceFollowupDTO out = resourceFollowUpMapper.mapToDto(resourceFollowupCreated);
        return out;
    }

}
