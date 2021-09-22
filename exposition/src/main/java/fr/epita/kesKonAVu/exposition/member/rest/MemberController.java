package fr.epita.kesKonAVu.exposition.member.rest;

import com.fasterxml.jackson.core.JsonToken;
import fr.epita.kesKonAVu.application.user.MemberService;
import fr.epita.kesKonAVu.config.security.jwt.JwtResponse;
import fr.epita.kesKonAVu.config.security.jwt.JwtTokenManager;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import fr.epita.kesKonAVu.domain.user.Member;
import fr.epita.kesKonAVu.exposition.followUp.rest.FollowUpDTO;
import fr.epita.kesKonAVu.exposition.followUp.rest.FollowUpMapper;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/member")
@CrossOrigin
public class MemberController {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    FollowUpMapper followUpMapper;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtTokenManager jwtTokenUtil;

    private static final Logger LOG = LoggerFactory.getLogger(MemberController.class);

    @GetMapping(value="/{pseudo}", produces={"application/json"})
    public MemberDTO getMemberAccountData(@PathVariable("pseudo") String pseudo){
        Member member = memberService.findOne(pseudo);
        return memberMapper.mapToDto(member);
    }

    @PostMapping(value = "/create", produces={"application/json"}, consumes = {"application/json"})
    @ApiOperation("This operation allows to create a new member in KeskonAvu and get a Token")
    public HttpEntity<?> createNewMember(@Valid @RequestBody MemberDTOLight memberDTOLight){
        LOG.warn("Demande création compte pseudo / email = " + memberDTOLight.getPseudo() + " / " + memberDTOLight.getEmail());

        // Création du membre dans le registre
        final Member memberToCreate = memberMapper.mapLightToEntity(memberDTOLight);
        final Member memberCreated = memberService.createMember(memberToCreate);

        // Construction du token avec les informations du member
        final UserDetails userDetails = userDetailsService.loadUserByUsername(memberCreated.getIdMember());
        final String token = jwtTokenUtil.generateToken(userDetails);

        //Construction de la réponse
        final MemberAuthenticatedDTO memberAuthenticatedDTO = memberMapper.mapToLoggedMember(memberCreated);
        memberAuthenticatedDTO.setJwtToken(new JwtResponse(token));

        LOG.info("Création compte OK - pseudo / email / idMember = " + memberDTOLight.getPseudo() + " / " + memberDTOLight.getEmail() + " / " + memberCreated.getIdMember());
        return ResponseEntity.ok(memberAuthenticatedDTO);
    }

    @GetMapping(value="/followups", produces={"application/json"})
    @ApiOperation("This operation allows to get all member followUps")
    public MemberWithFollowupsDTO getFollowUps(@RequestHeader("Authorization") String requestTokenHeader,
                                               @RequestParam(value = "type", defaultValue = "all") String type) {

        //Spring Security => Contrôle préalable de la validité du token transmis
        //Récupération de l'id Member à partir du JWT du header de la requête
        final String jwtToken = requestTokenHeader.substring(7);
        final String idMember = jwtTokenUtil.getIdMemberFromToken(jwtToken);

        LOG.info("Demande consultation Followup member / type = " + idMember +  " / " + type );
        Optional<ResourceTypeEnum> typeResourceFollowUp;
        //Contrôle du format du RequestParam
        switch (type){
            case "serie" : {
                typeResourceFollowUp = Optional.of(ResourceTypeEnum.SERIE);
                break;
            }
            case "movie" :  {
                typeResourceFollowUp = Optional.of(ResourceTypeEnum.MOVIE);
                break;
            }
            case "all" : {
                typeResourceFollowUp = Optional.empty();
                break;
            }
            default : {
                LOG.error("Demande consultation Followup KO : member / type " + idMember +  " / " + type );
                throw new IllegalArgumentException("Request Followups not Authorized with type = " + type);
            }
        }

        //Recherche des followUps du member
        Member member = memberService.findByIdWithAllResourceFollowUps(idMember, typeResourceFollowUp);

        MemberWithFollowupsDTO memberToRetrieved = new MemberWithFollowupsDTO();
        Set<FollowUpDTO> setTocreate =
                member.getFollowUps()
                        .stream()
                                .map(r -> followUpMapper.mapToDto(r))
                                        .collect(Collectors.toSet());
        memberToRetrieved.setResourceFollowUpS(setTocreate);

        LOG.info("Demande consultation Followup OK - member / Total = " + idMember + " / " + memberToRetrieved.getResourceFollowUpS().size());

        return memberToRetrieved;
    }

}
