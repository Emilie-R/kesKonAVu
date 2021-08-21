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

<<<<<<< HEAD
    @GetMapping(value="/{id}", produces={"application/json"})
    public FollowupDTO getResourceFollowUp(@PathVariable("id") Long id){
        FollowUp in = followUpService.findOne(id);
=======
    @GetMapping(value="/{idMember}", produces={"application/json"})
    public FollowupDTO getResourceFollowUp(@PathVariable("idMember") Long idMember){
        FollowUp in = resourceFollowUpService.findOne(idMember);
>>>>>>> 78634657e037e64b5c48e7b5be311cca339f4b45
        return followUpMapper.mapToDto(in);
    }

    @PostMapping(value = "/create", consumes = {"application/json"})
    public FollowupDTO createNewFollowUp(@Valid @RequestBody FollowUpDTOLight followUpDTOLight) {
        FollowUp FollowUp = followUpMapper.mapToEntity(followUpDTOLight);
        FollowUp followUpSaved = followUpService.createNewFollowUp(FollowUp);
        return followUpMapper.mapToDto(followUpSaved);
    }

<<<<<<< HEAD
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

=======
    @DeleteMapping(value="/{id}")
    public Long deleteFollowUp(@PathVariable("id") Long idFollowUp){
        return followUpService.deleteFollowUp(idFollowUp);
    }

>>>>>>> 78634657e037e64b5c48e7b5be311cca339f4b45
}
