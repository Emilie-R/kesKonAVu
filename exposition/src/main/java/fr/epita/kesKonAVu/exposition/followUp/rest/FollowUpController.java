package fr.epita.kesKonAVu.exposition.followUp.rest;

import fr.epita.kesKonAVu.application.SerieProgression.UpdateSerieProgressionApplicationService;
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

    @Autowired
    UpdateSerieProgressionApplicationService updateSerieProgressionApplicationService;

    /**
     *
     * @param id du followUp
     * @return a followDTO without its episodeFollowUp
     */
    @GetMapping(value="/{id}", produces={"application/json"})
    public FollowUpDTO getFollowUp (@PathVariable("id") Long id){
        FollowUp in = followUpService.findOne(id);

        return followUpMapper.mapToDto(in);
    }

    @PostMapping(value = "/create", consumes = {"application/json"},produces={"application/json"})
    public FollowUpDTO createNewFollowUp(@Valid @RequestBody FollowUpDTOLight followUpDTOLight) {
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


}
