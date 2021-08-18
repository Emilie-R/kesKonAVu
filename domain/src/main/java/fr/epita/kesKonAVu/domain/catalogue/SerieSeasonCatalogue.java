package fr.epita.kesKonAVu.domain.catalogue;

import java.util.List;

public class SerieSeasonCatalogue {

    private String season;
    private String totalSeasons;

    List<EpisodeCatalogue> episodes;

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

    public List<EpisodeCatalogue> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<EpisodeCatalogue> episodes) {
        this.episodes = episodes;
    }
}
