package fr.epita.kesKonAVu.exposition.followUp.rest;

import fr.epita.kesKonAVu.application.followUp.FollowUpService;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/followup")
public class FollowUpController {

    @Autowired
    FollowUpService followUpService;

    @Autowired
    FollowUpMapper followUpMapper;


    @GetMapping(value="/{idMember}", produces={"application/json"})
    public FollowupDTO getResourceFollowUp(@PathVariable("idMember") Long idMember){
        FollowUp in = followUpService.findOne(idMember);

        return followUpMapper.mapToDto(in);
    }

    @PostMapping(value = "/create", consumes = {"application/json"},produces={"application/json"})
    public FollowupDTO createNewFollowUp(@Valid @RequestBody FollowUpDTOLight followUpDTOLight) {
        FollowUp FollowUp = followUpMapper.mapToEntity(followUpDTOLight);
        FollowUp followUpSaved = followUpService.createNewFollowUp(FollowUp);
        return followUpMapper.mapToDto(followUpSaved);
    }


//    Mise à jour du followUp

    /**
     * Mise à jour du followUp : note ou status
     * @param entered (followUpUpdateDTOLight)
     * @return "OK" ou "KO"
     */
    @PutMapping(value = "/update",consumes = {"application/json"},produces={"application/json"})
    public String updateFollowUp(@Valid @RequestBody FollowUpUpdateDTOLight entered) {
        FollowUp intermediate = followUpService.findOne(entered.getIdMember());
        if(entered.getStatus() != intermediate.getStatus()){
            intermediate.setStatus(entered.getStatus());
        }
        if(entered.getNote() != null && entered.getNote() != intermediate.getNote()){
            intermediate.setNote(entered.getNote());
        }
        return followUpService.updateFollowUp(intermediate);
    }

    @DeleteMapping(value="/{id}")
    public Long deleteFollowUp(@PathVariable("id") Long idFollowUp){
        return followUpService.deleteFollowUp(idFollowUp);
    }

    /**
     * Permet d'enregistrer la progression saisie par l'utilisateur pour une série donnée
     * @param entered FollowUpProgressionDTO
     * @return id (Long) du FollowUp mis à jour en base
     */
    @PutMapping(value = "/progression",consumes = {"application/json"},produces={"application/json"})
    public Long updateProgressionFollowUp(@Valid @RequestBody FollowUpProgressionDTO entered) {
        FollowUpProgressionDTOMapper followUpProgressionDTOMapper = new FollowUpProgressionDTOMapper();
        FollowUp converted = followUpProgressionDTOMapper.mapToEntity(entered);
        return followUpService.SaveSerieProgression(converted);
    }

}
