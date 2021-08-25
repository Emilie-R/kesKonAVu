package fr.epita.kesKonAVu.exposition.SerieProgression.rest;

import fr.epita.kesKonAVu.application.SerieProgression.UpdateSerieProgressionApplicationService;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.exposition.followUp.rest.FollowUpDTO;
import fr.epita.kesKonAVu.exposition.followUp.rest.FollowUpMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/progression")
public class FollowUpProgressionController {

    FollowUpProgressionDTOMapper followUpProgressionDTOMapper = new FollowUpProgressionDTOMapper();
    @Autowired
    UpdateSerieProgressionApplicationService progressionService;

    /**
     *
     * @param idFollowUp
     * @return
     */
    @GetMapping(value = "/edit/{idFollowUp}")
    @ApiOperation("edit serie progression")
    public FollowUpProgressionDTO getEpisodesFollowUps(@PathVariable("idFollowUp") Long idFollowUp){
        FollowUpProgressionDTO out = followUpProgressionDTOMapper.mapToDto(progressionService.getEpisodeFollowUpList(idFollowUp));
        return out;
    }

    @PostMapping(value = "/save")
    @ApiOperation("update serie progression")
    public Long saveProgressionSerie(@RequestBody FollowUpProgressionDTO entered){
        FollowUp toSend = followUpProgressionDTOMapper.mapToEntity(entered);
        return progressionService.saveSerieProgression(toSend);
    }

    /**
     * Permet d'enregistrer la progression saisie par l'utilisateur pour une série donnée
     * @param entered FollowUpDTO
     * @return id (Long) du FollowUp mis à jour en base
     */
    @PutMapping(value = "/update",consumes = {"application/json"},produces={"application/json"})
    public Long updateProgressionFollowUp(@Valid @RequestBody FollowUpDTO entered) {
        FollowUpMapper followUpDTOMapper = new FollowUpMapper();
        FollowUp converted = followUpDTOMapper.mapToEntity(entered);
        return progressionService.updateProgressionSerie(converted);
    }
}
