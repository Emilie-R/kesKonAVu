package fr.epita.kesKonAVu.exposition.followUp.rest;

import fr.epita.kesKonAVu.application.followUp.FollowUpService;
import fr.epita.kesKonAVu.application.followUp.ResourceFollowUpService;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.followUp.FollowUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/followup")
public class FollowUpController {

    @Autowired
    ResourceFollowUpService resourceFollowUpService;

    @Autowired
    FollowUpService followUpService;

    @Autowired
    FollowUpMapper followUpMapper;

    @GetMapping(value="/{id}", produces={"application/json"})
    public FollowupDTO getResourceFollowUp(@PathVariable("id") Long id){
        FollowUp in = resourceFollowUpService.findOne(id);
        return followUpMapper.mapToDto(in);
    }

    @PostMapping(value = "/create", consumes = {"application/json"})
    public FollowupDTO createNewFollowUp(@Valid @RequestBody ResourceFollowUpDTOLight resourceFollowUpDTOLight) {
        FollowUp resourceFollowUp = followUpMapper.mapToEntity(resourceFollowUpDTOLight);
        FollowUp resourceFollowUpSaved = followUpService.createNewFollowUp(resourceFollowUp);
        return followUpMapper.mapToDto(resourceFollowUpSaved);
    }

    @PatchMapping("/{id}/{note}")
    public ResponseEntity< FollowUp> updateFollowUpPartially(@PathVariable Long idFollowUp, @PathVariable Integer note) {
        try {
            FollowUp  FollowUp =  followUpService.findOne(idFollowUp);
            FollowUp.setNote(note);
            return new ResponseEntity< FollowUp>( followUpService.createNewFollowUp(FollowUp), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
