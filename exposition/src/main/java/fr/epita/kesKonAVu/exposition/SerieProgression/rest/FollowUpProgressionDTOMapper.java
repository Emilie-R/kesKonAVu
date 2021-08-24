package fr.epita.kesKonAVu.exposition.SerieProgression.rest;

import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeFollowUp;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.exposition.common.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class FollowUpProgressionDTOMapper extends AbstractMapper<FollowUp, FollowUpProgressionDTO> {

    @Override
    public FollowUpProgressionDTO mapToDto (FollowUp entity) {
        return null;
    }

    @Override
    public FollowUp mapToEntity (FollowUpProgressionDTO dto) {
        FollowUp out = new FollowUp();
        out.setIdFollowUp(dto.id);
        EpisodeFollowUp episodeW;
        for (int i = 0; i < dto.getEpisodeList().size()-1; i++) {
            episodeW = new EpisodeFollowUp();
            if (dto.getIdFollowUpList().get(i) != null){
            episodeW.setId(dto.getIdFollowUpList().get(i));}
            episodeW.setStatus(dto.episodeStatusEnumList.get(i));
            episodeW.setEpisode(dto.getEpisodeList().get(i));
            out.addEpisodeFollowup(episodeW);
            episodeW = null;
        }
        return out;
    }
}
