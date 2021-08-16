package fr.epita.kesKonAVu.exposition.followUp.rest;

import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;
import fr.epita.kesKonAVu.exposition.common.AbstractMapper;
import fr.epita.kesKonAVu.exposition.member.rest.MemberMapper;
import fr.epita.kesKonAVu.exposition.resource.rest.ResourceMapper;

public class ResourceFollowUpMapper extends AbstractMapper<ResourceFollowUp, ResourceFollowupDTO> {

    ResourceMapper resourceMapper = new ResourceMapper();

    MemberMapper memberMapper = new MemberMapper();

    @Override
    public ResourceFollowupDTO mapToDto (ResourceFollowUp entity) {
        if(entity == null){
            return null;
        }
        ResourceFollowupDTO result = new ResourceFollowupDTO();
        result.setIdFollowUp(entity.getIdFollowUp());
        result.setResourceDTO(resourceMapper.mapToDto(entity.getResource()));
        result.setMemberDTO(memberMapper.mapToDto(entity.getMember()));
        result.setNote(entity.getNote());
        result.setStatus(entity.getStatus());
        result.setLastModificationDate(entity.getLastModificationDate());
        return result;
    }

    @Override
    public ResourceFollowUp mapToEntity (ResourceFollowupDTO dto) {
        if(dto == null){
            return null;
        }
        ResourceFollowUp result = new ResourceFollowUp();
        result.setIdFollowUp(dto.getIdFollowUp());
        result.setResource(resourceMapper.mapToEntity(dto.getResourceDTO()));
        result.setMember(memberMapper.mapToEntity(dto.getMemberDTO()));
        result.setNote(dto.getNote());
        result.setStatus(dto.getStatus());
        result.setLastModificationDate(dto.getLastModificationDate());
        return result;
    }
}
