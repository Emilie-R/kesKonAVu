package fr.epita.kesKonAVu.exposition.resource.rest;

import fr.epita.kesKonAVu.domain.resource.Serie;
import fr.epita.kesKonAVu.exposition.common.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class SerieMapper extends AbstractMapper<Serie, SerieDTO> {
    @Override
    public SerieDTO mapToDto (Serie entity) {
        if(entity == null){
            return null;
        }
        SerieDTO dto = new SerieDTO();
        dto.setTitle(entity.getTitle());
        dto.setYear(entity.getYear());
        dto.setPictureUrl(entity.getPictureUrl());
        dto.setDirector(entity.getDirector());
        dto.setSynopsis(entity.getSynopsis());
        dto.setCategory(entity.getCategory());
        dto.setDuration(entity.getDuration());
        dto.setActors(entity.getActors());
        dto.setExternalKey(entity.getExternalKey());
        dto.setNumberOfEpisodes(entity.getNumberOfEpisodes());
        if(entity.getNumberOfSeasons() != null){
        dto.setNumberOfSeasons(entity.getNumberOfSeasons());}
        dto.setIdResource(entity.getIdResource());
        return dto;
    }

    @Override
    public Serie mapToEntity (SerieDTO dto) {
        if(dto == null){
            return null;
        }
        Serie entity = new Serie();
        entity.setTitle(dto.getTitle());
        entity.setYear(dto.getYear());
        entity.setPictureUrl(dto.getPictureUrl());
        entity.setDirector(dto.getDirector());
        entity.setSynopsis(dto.getSynopsis());
        entity.setCategory(dto.getCategory());
        entity.setDuration(dto.getDuration());
        entity.setActors(dto.getActors());
        entity.setExternalKey("OMDB api");
        entity.setNumberOfEpisodes(dto.getNumberOfEpisodes());
        entity.setNumberOfSeasons(dto.getNumberOfSeasons());
        entity.setIdResource(dto.getIdResource());
        return entity;
    }
}
