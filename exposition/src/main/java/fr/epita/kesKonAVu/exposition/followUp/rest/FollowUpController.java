package fr.epita.kesKonAVu.exposition.followUp.rest;

import fr.epita.kesKonAVu.application.followUp.FollowUpService;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/followup")
public class FollowUpController {

    @Autowired
    FollowUpService followUpService;

    @Autowired
    FollowUpMapper followUpMapper;

    @GetMapping(value="/{id}", produces={"application/json"})
    public FollowupDTO getResourceFollowUp(@PathVariable("id") Long id){
        FollowUp in = followUpService.findOne(id);
        return followUpMapper.mapToDto(in);
    }

    @PostMapping(value = "/create", consumes = {"application/json"})
    public FollowupDTO createNewFollowUp(@Valid @RequestBody ResourceFollowUpDTOLight resourceFollowUpDTOLight) {
        FollowUp resourceFollowUp = followUpMapper.mapToEntity(resourceFollowUpDTOLight);
        FollowUp resourceFollowUpSaved = followUpService.createNewFollowUp(resourceFollowUp);
        return followUpMapper.mapToDto(resourceFollowUpSaved);
    }

//    Mise à jour de la note du followUp
    @PutMapping("/note/{id}/{note}")
    public String updateRating(@PathVariable("id") Long idFollowUp, @PathVariable("note") Integer note) {


        return followUpService.updateRating(idFollowUp,note);
    }

//    Mise à jour du status du followUp
    @PutMapping("/status/{id}")
    public String updateStatus(@PathVariable("id") Long idFollowUp, @RequestBody StatusEnum statusEnum) {

        return followUpService.updateStatus(idFollowUp,statusEnum);
        }

}
