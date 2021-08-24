package fr.epita.kesKonAVu.exposition.SerieProgression.rest;

import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeFollowUp;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.exposition.EpisodeFollowUp.rest.EpisodeFollowUpDTO;
import fr.epita.kesKonAVu.exposition.common.AbstractMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FollowUpProgressionDTOMapper extends AbstractMapper<FollowUp, FollowUpProgressionDTO> {

    @Override
    public FollowUpProgressionDTO mapToDto (FollowUp entity) {

        FollowUpProgressionDTO out = new FollowUpProgressionDTO();
        out.setIdFollowUp(entity.getIdFollowUp());

        List<EpisodeFollowUpDTO> listOut = new ArrayList<>();

        for (EpisodeFollowUp e : entity.getEpisodeFollowUps()) {
            EpisodeFollowUpDTO episodeOut = new EpisodeFollowUpDTO();
            episodeOut.setIdEpisodeFollowUp(e.getId());
            episodeOut.setEpisodeStatusEnum(e.getStatus());
            episodeOut.setEpisode(e.getEpisode());
            listOut.add(episodeOut);
            out.setEpisodeFollowUpDTOList(listOut);
        }
        return out;
    }

    @Override
    public FollowUp mapToEntity (FollowUpProgressionDTO dto) {
        FollowUp out = new FollowUp();
        out.setIdFollowUp(dto.getIdFollowUp());
        for (EpisodeFollowUpDTO e : dto.getEpisodeFollowUpDTOList()) {
            EpisodeFollowUp episodeOut = new EpisodeFollowUp();
            if (e.getIdEpisodeFollowUp() != null) {
                episodeOut.setId(e.getIdEpisodeFollowUp());
            }
            episodeOut.setStatus(e.getEpisodeStatusEnum());
            episodeOut.setEpisode(e.getEpisode());
            out.addEpisodeFollowup(episodeOut);
            episodeOut= null;
        }
        return out;
    }
}
