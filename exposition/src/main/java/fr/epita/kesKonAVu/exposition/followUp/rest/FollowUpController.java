package fr.epita.kesKonAVu.exposition.followUp.rest;

import fr.epita.kesKonAVu.application.SerieProgression.UpdateSerieProgressionApplicationService;
import fr.epita.kesKonAVu.application.followUp.FollowUpService;
import fr.epita.kesKonAVu.config.security.jwt.JwtTokenManager;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.user.Member;
import io.swagger.annotations.ApiOperation;
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

    @Autowired
    JwtTokenManager jwtTokenUtil;

    /**
     *
     * @param id du followUp
     * @return a followDTO without its episodeFollowUp
     */
    @ApiOperation("This operation allows to retrieve one followup")
    @GetMapping(value="/{id}", produces={"application/json"})
    public FollowUpDTO getFollowUp (@PathVariable("id") Long id){
        FollowUp in = followUpService.findOne(id);

        return followUpMapper.mapToDto(in);
    }

    @ApiOperation("This operation allows to create a new followup serie or movie, for a member")
    @PostMapping(value = "/create", consumes = {"application/json"},produces={"application/json"})
    public FollowUpDTO createNewFollowUp(@RequestHeader("Authorization") String requestTokenHeader,
                                         @Valid @RequestBody FollowUpDTOLight followUpDTOLight) {

        //Spring Security => Contrôle prélable de la validité du token transmis
        //Récupération de l'id Member à partir du JWT du header de la requête
        final String jwtToken = requestTokenHeader.substring(7);
        final String idMember = jwtTokenUtil.getIdMemberFromToken(jwtToken);
        final Member member = new Member();
        member.setIdMember(idMember);

        FollowUp followUp = followUpMapper.mapToEntity(followUpDTOLight);
        followUp.setMember(member);

        FollowUp followUpSaved = followUpService.createNewFollowUpFromImdbId(followUp);
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
        FollowUp intermediate = followUpService.findOne(entered.getIdFollowUp());

        if(entered.getStatus() != intermediate.getStatus()){
            intermediate.setStatus(entered.getStatus());
        }
        if(entered.getNote() != null && entered.getNote()!= intermediate.getNote()){
            intermediate.setNote(entered.getNote());
        }
        return followUpService.updateFollowUp(intermediate);
    }

    @DeleteMapping(value="/{id}")
    public void deleteFollowUp(@PathVariable("id") Long idFollowUp){
        followUpService.deleteFollowUp(idFollowUp);
    }


}
