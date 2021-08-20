package fr.epita.kesKonAVu.exposition.followUp.rest;

import fr.epita.kesKonAVu.application.followUp.FollowUpService;
import fr.epita.kesKonAVu.application.followUp.ResourceFollowUpService;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/followup")
public class FollowUpController {

    @Autowired
    ResourceFollowUpService resourceFollowUpService;

    @Autowired
    FollowUpService followUpService;

    @Autowired
    FollowUpMapper followUpMapper;

    @GetMapping(value="/{idMember}", produces={"application/json"})
    public FollowupDTO getResourceFollowUp(@PathVariable("idMember") Long idMember){
        FollowUp in = resourceFollowUpService.findOne(idMember);
        return followUpMapper.mapToDto(in);
    }

    @PostMapping(value = "/create", consumes = {"application/json"})
    public FollowupDTO createNewFollowUp(@Valid @RequestBody FollowUpDTOLight followUpDTOLight) {
        FollowUp FollowUp = followUpMapper.mapToEntity(followUpDTOLight);
        FollowUp followUpSaved = followUpService.createNewFollowUp(FollowUp);
        return followUpMapper.mapToDto(followUpSaved);
    }

    @DeleteMapping(value="/{id}")
    public Long deleteFollowUp(@PathVariable("id") Long idFollowUp){
        return followUpService.deleteFollowUp(idFollowUp);
    }

}
