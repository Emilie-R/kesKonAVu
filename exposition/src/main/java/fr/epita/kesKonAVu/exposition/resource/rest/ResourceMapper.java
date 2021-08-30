
package fr.epita.kesKonAVu.exposition.resource.rest;

import fr.epita.kesKonAVu.domain.resource.CatalogReferenceEnum;
import fr.epita.kesKonAVu.domain.resource.ExternalKey;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.exposition.common.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class ResourceMapper extends AbstractMapper<Resource, ResourceDTO> {

    @Override
    public ResourceDTO mapToDto (Resource entity) {
        if(entity == null){
            return null;
        }
        ResourceDTO dto = new ResourceDTO();
        dto.setTitle(entity.getTitle());
        dto.setYear(entity.getYear());
        dto.setPictureUrl(entity.getPictureUrl());
        dto.setDirector(entity.getDirector());
        dto.setSynopsis(entity.getSynopsis());
        dto.setCategory(entity.getCategory());
        dto.setDuration(entity.getDuration());
        dto.setActors(entity.getActors());
        dto.setIdResource(entity.getIdResource());
        dto.setExternalKey(entity.getExternalKey());
        dto.setExternalCatalogName(entity.getExternalCatalogName());
        dto.setResourceType(entity.getResourceType());
        return dto;
    }

    @Override
    public Resource mapToEntity (ResourceDTO dto) {
        if(dto == null){
            return null;
        }
        Resource entity = new Resource();
        entity.setTitle(dto.getTitle());
        entity.setYear(dto.getYear());
        entity.setPictureUrl(dto.getPictureUrl());
        entity.setDirector(dto.getDirector());
        entity.setSynopsis(dto.getSynopsis());
        entity.setCategory(dto.getCategory());
        entity.setDuration(dto.getDuration());
        entity.setActors(dto.getActors());
        entity.setExternalKey(dto.getExternalKey());
        entity.setExternalCatalogName(dto.getExternalCatalogName());
        entity.setResourceType(dto.getResourceType());
        return entity;
    }
}
