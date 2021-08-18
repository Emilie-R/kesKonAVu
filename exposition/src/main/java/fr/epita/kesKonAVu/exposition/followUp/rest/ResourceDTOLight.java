package fr.epita.kesKonAVu.exposition.followUp.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
public class ResourceDTOLight {

    @JsonProperty("resourceType")
    @NotNull
    private ResourceTypeEnum typeResource;

    @JsonProperty("imdbId")
    @NotBlank
    private String imdbId;

    public ResourceTypeEnum getTypeResource() {
        return typeResource;
    }

    public void setTypeResource(ResourceTypeEnum typeResource) {
        this.typeResource = typeResource;
    }

    public @NotNull String getImdbId() {
        return imdbId;
    }

    public void setImdbId(@NotNull String imdbId) {
        this.imdbId = imdbId;
    }



}
