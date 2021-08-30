package fr.epita.kesKonAVu.exposition.EpisodeFollowUp.rest;

import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeStatusEnum;
import fr.epita.kesKonAVu.domain.resource.Episode;

public class EpisodeFollowUpDTO {

    Long idEpisodeFollowUp;
    Episode episode;
    EpisodeStatusEnum episodeStatusEnum; // that user has written

    public Long getIdEpisodeFollowUp ( ) {
        return idEpisodeFollowUp;
    }

    public void setIdEpisodeFollowUp (Long idEpisodeFollowUp) {
        this.idEpisodeFollowUp = idEpisodeFollowUp;
    }

    public Episode getEpisode ( ) {
        return episode;
    }

    public void setEpisode (Episode episode) {
        this.episode = episode;
    }

    public EpisodeStatusEnum getEpisodeStatusEnum ( ) {
        return episodeStatusEnum;
    }

    public void setEpisodeStatusEnum (EpisodeStatusEnum episodeStatusEnum) {
        this.episodeStatusEnum = episodeStatusEnum;
    }
}
