package fr.epita.kesKonAVu.exposition.SerieProgression.rest;

import fr.epita.kesKonAVu.application.SerieProgression.UpdateSerieProgressionApplicationService;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation("edit progression")
    public FollowUpProgressionDTO getEpisodesFollowUps(@PathVariable("idFollowUp") Long idFollowUp){
        FollowUpProgressionDTO out = followUpProgressionDTOMapper.mapToDto(progressionService.getEpisodeFollowUpList(idFollowUp));
        return out;
    }

    @PostMapping(value = "/save")
    @ApiOperation("save progression")
    public Long saveProgressionSerie(@RequestBody FollowUpProgressionDTO entered){
        FollowUp toSend = followUpProgressionDTOMapper.mapToEntity(entered);
        return progressionService.saveSerieProgression(toSend);
    }
}
