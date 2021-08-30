package fr.epita.kesKonAVu.exposition.followUp.rest;

import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.user.Member;
import fr.epita.kesKonAVu.exposition.common.AbstractMapper;
import fr.epita.kesKonAVu.exposition.resource.rest.ResourceMapper;
import org.springframework.stereotype.Component;

@Component
public class FollowUpMapper extends AbstractMapper<FollowUp, FollowUpDTO> {

    ResourceMapper resourceMapper = new ResourceMapper();

    @Override
    public FollowUpDTO mapToDto (FollowUp entity) {
        if(entity == null){
            return null;
        }
        FollowUpDTO result = new FollowUpDTO();
        result.setIdFollowUp(entity.getIdFollowUp());
        result.setResourceDTO(resourceMapper.mapToDto(entity.getResource()));
        if (entity.getNote() != null ) { result.setNote(entity.getNote()); }
        result.setStatus(entity.getStatus());
        result.setLastModificationDate(entity.getLastModificationDate());
        result.setProgression(entity.getProgression());
        result.setNumberOfUnseenEpisodes(entity.getNumberOfUnseenEpisodes());

        return result;
    }

    @Override
    public FollowUp mapToEntity (FollowUpDTO dto) {
        if(dto == null){
            return null;
        }
        FollowUp result = new FollowUp();
        result.setIdFollowUp(dto.getIdFollowUp());
        result.setResource(resourceMapper.mapToEntity(dto.getResourceDTO()));
        result.setNote(dto.getNote());
        result.setStatus(dto.getStatus());
        result.setLastModificationDate(dto.getLastModificationDate());
        result.setProgression(dto.getProgression());
        result.setNumberOfUnseenEpisodes(dto.getNumberOfUnseenEpisodes());
        return result;
    }

    public FollowUp mapToEntity (FollowUpDTOLight dtoLight) {
        if (dtoLight == null) {
            return null;
        }

        FollowUp followUp = new FollowUp();

        Resource resource = new Resource();
        if (dtoLight.getResourceDTOLight() != null) {
            resource.setResourceType(dtoLight.getResourceDTOLight().getTypeResource());
            resource.setExternalKey(dtoLight.getResourceDTOLight().getImdbId());
            resource.setExternalCatalogName(dtoLight.getResourceDTOLight().getExternalCatalogName());
        }

        followUp.setResource(resource);
        followUp.setStatus(dtoLight.getStatus());

        return followUp;
    }
}
