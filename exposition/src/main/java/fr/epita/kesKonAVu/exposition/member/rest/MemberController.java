package fr.epita.kesKonAVu.exposition.member.rest;

import fr.epita.kesKonAVu.application.user.MemberService;
import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;
import fr.epita.kesKonAVu.domain.user.Member;
import fr.epita.kesKonAVu.exposition.followUp.rest.ResourceFollowUpMapper;
import fr.epita.kesKonAVu.exposition.followUp.rest.ResourceFollowupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    ResourceFollowUpMapper resourceFollowUpMapper;

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
    public MemberWithResourceFollowupsDTO getResourcesFlollowUps(@PathVariable("id") Long idMember){

        Member member = memberService.findByIdWithAllResourceFollowUps(idMember);

        MemberWithResourceFollowupsDTO memberToRetrieved = new MemberWithResourceFollowupsDTO();
        memberToRetrieved.setIdMember(member.getIdMember());
        Set<ResourceFollowupDTO> setTocreate =
                member.getResourceFollowUps()
                        .stream()
                                .map(r -> resourceFollowUpMapper.mapToDto(r))
                                        .collect(Collectors.toSet());
        memberToRetrieved.setResourceFollowUpS(setTocreate);

        return memberToRetrieved;
    }

}
