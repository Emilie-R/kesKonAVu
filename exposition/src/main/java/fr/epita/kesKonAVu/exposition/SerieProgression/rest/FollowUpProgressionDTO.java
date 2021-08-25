package fr.epita.kesKonAVu.exposition.SerieProgression.rest;

import fr.epita.kesKonAVu.exposition.EpisodeFollowUp.rest.EpisodeFollowUpDTO;

import java.util.List;

public class FollowUpProgressionDTO {
    Long idFollowUp; // the id of FollowUp into the database
    List<EpisodeFollowUpDTO> episodeFollowUpDTOList;

    public FollowUpProgressionDTO ( ) {
    }

    public Long getIdFollowUp ( ) {
        return idFollowUp;
    }

    public void setIdFollowUp (Long idFollowUp) {
        this.idFollowUp = idFollowUp;
    }

    public List<EpisodeFollowUpDTO> getEpisodeFollowUpDTOList ( ) {
        return episodeFollowUpDTOList;
    }

    public void setEpisodeFollowUpDTOList (List<EpisodeFollowUpDTO> episodeFollowUpDTOList) {
        this.episodeFollowUpDTOList = episodeFollowUpDTOList;
    }

}
