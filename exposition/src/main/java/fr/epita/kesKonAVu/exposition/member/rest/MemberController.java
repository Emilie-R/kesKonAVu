package fr.epita.kesKonAVu.exposition.member.rest;

import fr.epita.kesKonAVu.application.user.MemberService;
import fr.epita.kesKonAVu.config.security.jwt.JwtResponse;
import fr.epita.kesKonAVu.config.security.jwt.JwtTokenManager;
import fr.epita.kesKonAVu.domain.user.Member;
import fr.epita.kesKonAVu.exposition.followUp.rest.FollowUpMapper;
import fr.epita.kesKonAVu.exposition.followUp.rest.FollowupDTO;
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
    public HttpEntity<?> createNewMember(@RequestBody MemberDTOLight memberDTOLight){
        // Création du membre dans le registre
        final Member memberToCreate = memberMapper.mapLightToEntity(memberDTOLight);
        final Member memberCreated = memberService.createMember(memberToCreate);

        // Construction du token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(memberCreated.getPseudo());
        final String token = jwtTokenUtil.generateToken(userDetails);

        //Construction de la réponse
        final LoggedMemberDTO loggedMemberDTO = memberMapper.mapToLoggedMember(memberCreated);
        loggedMemberDTO.setJwtToken(new JwtResponse(token));

        return ResponseEntity.ok(loggedMemberDTO);
    }

    @GetMapping(value="/followup/{id}", produces={"application/json"})
    public MemberWithResourceFollowupsDTO getResourcesFlollowUps(@PathVariable("id") Long idMember){

        Member member = memberService.findByIdWithAllResourceFollowUps(idMember);

        MemberWithResourceFollowupsDTO memberToRetrieved = new MemberWithResourceFollowupsDTO();
        memberToRetrieved.setIdMember(member.getIdMember());
        Set<FollowupDTO> setTocreate =
                member.getFollowUps()
                        .stream()
                                .map(r -> followUpMapper.mapToDto(r))
                                        .collect(Collectors.toSet());
        memberToRetrieved.setResourceFollowUpS(setTocreate);

        return memberToRetrieved;
    }

}
