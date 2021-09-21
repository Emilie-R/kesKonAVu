package fr.epita.kesKonAVu.exposition.followUp.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import fr.epita.kesKonAVu.exposition.resource.rest.ResourceDTO;

import java.time.LocalDateTime;

public class FollowUpDTO {

    @JsonProperty("idFollowUp")
    private Long idFollowUp;

    @JsonProperty("Resource")
    private ResourceDTO resourceDTO;

    @JsonProperty("status")
    private StatusEnum status;

    @JsonProperty("note")
    private Integer note;

    @JsonProperty("lastModificationDate")
    private LocalDateTime lastModificationDate;

    @JsonProperty("progression")
    private Float progression;// Ce DTO gère (donc) les suivis de Films ET de séries

    @JsonProperty("numberOfUnseenEpisodes")
    private Integer numberOfUnseenEpisodes;

    public FollowUpDTO (){
    }

    public Long getIdFollowUp ( ) {
        return idFollowUp;
    }

    public void setIdFollowUp (Long idFollowUp) {
        this.idFollowUp = idFollowUp;
    }

    public ResourceDTO getResourceDTO ( ) {
        return resourceDTO;
    }

    public void setResourceDTO (ResourceDTO resourceDTO) {
        this.resourceDTO = resourceDTO;
    }

    public StatusEnum getStatus ( ) {
        return status;
    }

    public void setStatus (StatusEnum status) {
        this.status = status;
    }

    public LocalDateTime getLastModificationDate ( ) {
        return lastModificationDate;
    }

    public void setLastModificationDate (LocalDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public Integer getNote ( ) {
        return note;
    }

    public void setNote (Integer note) {
        this.note = note;
    }

    public Float getProgression ( ) {
        return progression;
    }

    public void setProgression (Float progression) {
        this.progression = progression;
    }

    public Integer getNumberOfUnseenEpisodes ( ) {
        return numberOfUnseenEpisodes;
    }

    public void setNumberOfUnseenEpisodes (Integer numberOfUnseenEpisodes) {
        this.numberOfUnseenEpisodes = numberOfUnseenEpisodes;
    }
}
