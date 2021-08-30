package fr.epita.kesKonAVu.exposition.followUp.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public class FollowUpUpdateDTOLight {

    @JsonProperty("idFollowUp")
    @NotNull
    private Long idFollowUp;

    @JsonProperty("Resource")
    private ResourceDTOLight resourceDTOLight;

    @JsonProperty("note")
    private Integer note;

    @JsonProperty("status")
    @NotNull
    private StatusEnum status;

    public Long getIdFollowUp() {
        return idFollowUp;
    }

    public void setIdFollowUp(Long idFollowUp) {
        this.idFollowUp = idFollowUp;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Integer getNote ( ) {
        return note;
    }

    public void setNote (Integer note) {
        this.note = note;
    }

    public ResourceDTOLight getResourceDTOLight() {
        return resourceDTOLight;
    }

    public void setResourceDTOLight(ResourceDTOLight resourceDTOLight) {
        this.resourceDTOLight = resourceDTOLight;
    }
}
