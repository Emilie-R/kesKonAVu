package fr.epita.kesKonAVu.exposition.SerieProgression.rest;

import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeStatusEnum;
import fr.epita.kesKonAVu.domain.resource.Episode;

import java.util.List;

public class FollowUpProgressionDTO {
    Long id; // the id of FollowUp into the database
    List<Long> idEpisodeFollowUpList; //  id list of the episodes
    List<Episode> episodeList;
    List<EpisodeStatusEnum> episodeStatusEnumList; // that user has written


    public Long getId ( ) {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public List<Long> getIdFollowUpList ( ) {
        return idEpisodeFollowUpList;
    }

    public void setIdFollowUpList (List<Long> idFollowUpList) {
        this.idEpisodeFollowUpList = idFollowUpList;
    }

    public List<Episode> getEpisodeList ( ) {
        return episodeList;
    }

    public void setEpisodeList (List<Episode> episodeList) {
        this.episodeList = episodeList;
    }

    public List<EpisodeStatusEnum> getEpisodeStatusEnumList ( ) {
        return episodeStatusEnumList;
    }

    public void setEpisodeStatusEnumList (List<EpisodeStatusEnum> episodeStatusEnumList) {
        this.episodeStatusEnumList = episodeStatusEnumList;
    }
}
