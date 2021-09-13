package fr.epita.kesKonAVu.exposition.member.rest;

import fr.epita.kesKonAVu.application.user.MemberService;
import fr.epita.kesKonAVu.config.security.jwt.JwtResponse;
import fr.epita.kesKonAVu.config.security.jwt.JwtTokenManager;
import fr.epita.kesKonAVu.domain.user.Member;
import fr.epita.kesKonAVu.exposition.followUp.rest.FollowUpDTO;
import fr.epita.kesKonAVu.exposition.followUp.rest.FollowUpMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value="/{pseudo}", produces={"application/json"})
    public MemberDTO getMemberAccountData(@PathVariable("pseudo") String pseudo){
        Member member = memberService.findOne(pseudo);
        return memberMapper.mapToDto(member);
    }

    @PostMapping(value = "/create", produces={"application/json"}, consumes = {"application/json"})
    @ApiOperation("This operation allows to create a new member in KeskonAvu and get a Token")
    public HttpEntity<?> createNewMember(@RequestBody MemberDTOLight memberDTOLight){
        // Création du membre dans le registre
        final Member memberToCreate = memberMapper.mapLightToEntity(memberDTOLight);
        final Member memberCreated = memberService.createMember(memberToCreate);

        // Construction du token avec les informations du member
        final UserDetails userDetails = userDetailsService.loadUserByUsername(memberCreated.getIdMember());
        final String token = jwtTokenUtil.generateToken(userDetails);

        //Construction de la réponse
        final MemberAuthenticatedDTO memberAuthenticatedDTO = memberMapper.mapToLoggedMember(memberCreated);
        memberAuthenticatedDTO.setJwtToken(new JwtResponse(token));

        System.out.println("Token JWT : " + token);
        System.out.println("ID Member " + memberCreated.getIdMember());

        return ResponseEntity.ok(memberAuthenticatedDTO);
    }

    @GetMapping(value="/followups", produces={"application/json"})
    @ApiOperation("This operation allows to get all member followUps")
    public MemberWithFollowupsDTO getFollowUps(@RequestHeader("Authorization") String requestTokenHeader){
        //Spring Security => Contrôle prélable de la validité du token transmis
        //Récupération de l'id Member à partir du JWT du header de la requête
        final String jwtToken = requestTokenHeader.substring(7);
        final String idMember = jwtTokenUtil.getIdMemberFromToken(jwtToken);

        System.out.println("Token JWT : " + jwtToken);
        System.out.println("ID Member " + idMember);

        //Recherche des followUps du member
        Member member = memberService.findByIdWithAllResourceFollowUps(idMember);

        MemberWithFollowupsDTO memberToRetrieved = new MemberWithFollowupsDTO();
        Set<FollowUpDTO> setTocreate =
                member.getFollowUps()
                        .stream()
                                .map(r -> followUpMapper.mapToDto(r))
                                        .collect(Collectors.toSet());
        memberToRetrieved.setResourceFollowUpS(setTocreate);

        return memberToRetrieved;
    }

}
