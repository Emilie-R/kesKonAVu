package fr.epita.kesKonAVu.exposition.member.rest;

import fr.epita.kesKonAVu.domain.user.Member;
import fr.epita.kesKonAVu.exposition.common.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper extends AbstractMapper<Member,MemberDTO> {

    @Override
    public MemberDTO mapToDto(final Member entity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setIdMember(entity.getIdMember());
        memberDTO.setPseudo(entity.getPseudo());
        memberDTO.setEmail(entity.getEmail());
        memberDTO.setCreationDate(entity.getCreationDate());
        memberDTO.setRoles(entity.getRoles());
        memberDTO.setLastConnexionDateTime(entity.getLastConnexionDateTime());
        return memberDTO;
    }

    @Override
    public Member mapToEntity(final MemberDTO dto) {
        Member member = new Member();
        member.setIdMember(dto.getIdMember());
        member.setPseudo(dto.getPseudo());
        member.setEmail(dto.getEmail());
        member.setCreationDate(dto.getCreationDate());
        member.setRoles(dto.getRoles());
        member.setLastConnexionDateTime(dto.getLastConnexionDateTime());
        return null;
    }

    public Member mapLightToEntity(final MemberDTOLight dtoLight) {
        Member member = new Member();
        member.setPseudo(dtoLight.getPseudo());
        member.setPassword(dtoLight.getPassword());
        member.setEmail(dtoLight.getEmail());
        return member;
    }

    public MemberAuthenticatedDTO mapToLoggedMember(final Member entity) {
        MemberAuthenticatedDTO memberAuthenticatedDTO = new MemberAuthenticatedDTO();
        memberAuthenticatedDTO.setPseudo(entity.getPseudo());
        memberAuthenticatedDTO.setEmail(entity.getEmail());
        memberAuthenticatedDTO.setCreationDate(entity.getCreationDate());
        memberAuthenticatedDTO.setLastConnexionDateTime(entity.getLastConnexionDateTime());
        memberAuthenticatedDTO.setRoles(entity.getRoles());
        return memberAuthenticatedDTO;
    }
}
