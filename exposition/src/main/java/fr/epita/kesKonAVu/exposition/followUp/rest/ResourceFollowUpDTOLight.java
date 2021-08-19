package fr.epita.kesKonAVu.exposition.followUp.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeStatusEnum;
import fr.epita.kesKonAVu.domain.followUp.statusEnum;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public class ResourceFollowUpDTOLight {

    @JsonProperty("idMember")
    private Long idMember;

    @JsonProperty("Resource")
    @NotNull
    private ResourceDTOLight resourceDTOLight;

    @JsonProperty("status")
    private statusEnum status;

    public Long getIdMember() {
        return idMember;
    }

    public void setIdMember(Long idMember) {
        this.idMember = idMember;
    }

    public ResourceDTOLight getResourceDTOLight() {
        return resourceDTOLight;
    }

    public void setResourceDTOLight(ResourceDTOLight resourceDTOLight) {
        this.resourceDTOLight = resourceDTOLight;
    }

    public statusEnum getStatus() {
        return status;
    }

    public void setStatus(statusEnum status) {
        this.status = status;
    }
}
