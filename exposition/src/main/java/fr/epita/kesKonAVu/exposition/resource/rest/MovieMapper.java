package fr.epita.kesKonAVu.exposition.resource.rest;

import fr.epita.kesKonAVu.domain.resource.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public MovieDTO mapToDto(Movie entity) {
        MovieDTO dto = new MovieDTO();
        dto.setIdResource(entity.getIdResource());
        dto.setResourceType(entity.getResourceType());

        dto.setTitle(entity.getTitle());
        dto.setYear(entity.getYear());
        dto.setPictureUrl(entity.getPictureUrl());
        dto.setDirector(entity.getDirector());
        dto.setSynopsis(entity.getSynopsis());
        dto.setCategory(entity.getCategory());
        dto.setDuration(entity.getDuration());
        dto.setActors(entity.getActors());
        dto.setImdbId(entity.getImdbId());
        dto.setResourceDataSource(entity.getResourceDataSource());
        dto.setLastModificationDate(entity.getLastModificationDateTime());

        return dto;
   }
}
