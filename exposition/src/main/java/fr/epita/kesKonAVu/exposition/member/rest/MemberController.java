package fr.epita.kesKonAVu.exposition.member.rest;

import fr.epita.kesKonAVu.application.user.MemberService;
import fr.epita.kesKonAVu.domain.user.Member;
import fr.epita.kesKonAVu.exposition.followUp.rest.FollowUpMapper;
import fr.epita.kesKonAVu.exposition.followUp.rest.FollowupDTO;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value="/{id}", produces={"application/json"})
    public MemberDTO getMemberAccountData(@PathVariable("id") Long idMember){
        Member member = memberService.findOne(idMember);
        return memberMapper.mapToDto(member);
    }

    @PostMapping(value = "/create", produces={"application/json"}, consumes = {"application/json"})
    public MemberDTO createNewMember(@RequestBody MemberDTOLight memberDTOLight){
        Member memberToCreate = memberMapper.mapLightToEntity(memberDTOLight);
        Member memberCreated = memberService.createMember(memberToCreate);
        return memberMapper.mapToDto(memberCreated);
    }

    @GetMapping(value="/followup/{id}", produces={"application/json"})
    public MemberWithFollowupsDTO getResourcesFlollowUps(@PathVariable("id") Long idMember){

        Member member = memberService.findByIdWithAllResourceFollowUps(idMember);

        MemberWithFollowupsDTO memberToRetrieved = new MemberWithFollowupsDTO();
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
