package fr.epita.kesKonAVu.exposition.member.rest;

import fr.epita.kesKonAVu.application.user.MemberService;
import fr.epita.kesKonAVu.domain.user.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberMapper memberMapper;

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


}
