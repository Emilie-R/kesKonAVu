package fr.epita.kesKonAVu.exposition.followUp.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import fr.epita.kesKonAVu.exposition.resource.rest.ResourceDTO;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public class FollowUpDTOLight {

    @JsonProperty("Resource")
    @NotNull
    private ResourceDTO resourceDTO;

    @JsonProperty("status")
    @NotNull
    private StatusEnum status;

    public ResourceDTO getResourceDTO() {
        return resourceDTO;
    }

    public void setResourceDTO (ResourceDTO resourceDTOLight) {
        this.resourceDTO = resourceDTOLight;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
