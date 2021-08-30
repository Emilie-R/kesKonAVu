package fr.epita.kesKonAVu.exposition.followUp.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public class FollowUpDTOLight {

    @JsonProperty("Resource")
    @NotNull
    private ResourceDTOLight resourceDTOLight;

    @JsonProperty("status")
    private StatusEnum status;

    public ResourceDTOLight getResourceDTOLight() {
        return resourceDTOLight;
    }

    public void setResourceDTOLight(ResourceDTOLight resourceDTOLight) {
        this.resourceDTOLight = resourceDTOLight;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
