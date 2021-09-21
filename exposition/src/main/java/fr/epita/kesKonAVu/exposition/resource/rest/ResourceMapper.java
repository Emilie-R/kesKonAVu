
package fr.epita.kesKonAVu.exposition.resource.rest;

import fr.epita.kesKonAVu.domain.resource.Movie;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import fr.epita.kesKonAVu.domain.resource.Serie;
import fr.epita.kesKonAVu.exposition.common.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class ResourceMapper extends AbstractMapper<Resource, ResourceDTO>{

    public ResourceDTO mapToDto (Resource entity) {
        if(entity == null){
            return null;
        }
        ResourceDTO dto = new ResourceDTO();
        dto.setTitle(entity.getTitle());
        dto.setPictureUrl(entity.getPictureUrl());
        dto.setIdResource(entity.getIdResource());
        dto.setImdbId(entity.getImdbId());
        dto.setResourceType(entity.getResourceType());
        dto.setLastModificationDate(entity.getLastModificationDateTime());

        return dto;
    }

    @Override
    public Resource mapToEntity(ResourceDTO dto) {
        Resource entity;

        if (dto.getResourceType().equals(ResourceTypeEnum.MOVIE)){
            entity = new Movie();
        } else {
            entity = new Serie();
        }
        entity.setResourceType(dto.getResourceType());
        entity.setIdResource(dto.getIdResource());
        entity.setTitle(dto.getTitle());
        entity.setImdbId(dto.getImdbId());
        entity.setPictureUrl(dto.getPictureUrl());
        entity.setLastModificationDateTime(dto.getLastModificationDate());
        return entity;
    }
}
