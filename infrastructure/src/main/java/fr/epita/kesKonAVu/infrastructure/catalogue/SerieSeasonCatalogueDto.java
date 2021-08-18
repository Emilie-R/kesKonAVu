package fr.epita.kesKonAVu.infrastructure.catalogue;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SerieSeasonCatalogueDto {

    @JsonProperty("Response")
    private String response;

    /* Specific data if response = False */
    @JsonProperty("Error")
    private String error;

    @JsonProperty("Season")
    private String season;

    @JsonProperty("totalSeasons")
    private String totalSeasons;

    @JsonProperty("Episodes")
    List<EpisodeCatalogueDto> episodes;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(String totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public List<EpisodeCatalogueDto> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<EpisodeCatalogueDto> episodes) {
        this.episodes = episodes;
    }
}
