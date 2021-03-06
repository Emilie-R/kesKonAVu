package fr.epita.kesKonAVu.domain.SerieProgression;

import fr.epita.kesKonAVu.domain.common.BusinessException;
import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeStatusEnum;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import org.springframework.stereotype.Service;

@Service
public class CalculateProgressionServiceImpl implements CalculateProgressionService {

    @Override
    public Float calculateProgression (FollowUp in) {

        if (in.getResource() == null || in.getResource().getResourceType() == ResourceTypeEnum.MOVIE){
            throw new BusinessException("Must be a Serie to calculate a progression ! idfolllowUp = " + in.getIdFollowUp());
        }
        if (in.getEpisodeFollowUps() == null) {
            throw new BusinessException("There's no episode followed for this serie !");
        }

            Long episodesViewed = in.getEpisodeFollowUps().stream()
                    .filter(e -> e.getStatus() == EpisodeStatusEnum.VU)
                    .count();
        Long episodesUnviewed = in.getEpisodeFollowUps().stream()
                .filter(e -> e.getStatus() == EpisodeStatusEnum.AVOIR)
                .count();
        Long total = episodesUnviewed+episodesViewed;
        Float result = 0F;
        if (total > 0F ) {
                result = Float.valueOf(100 * episodesViewed / total);
            }
        return result;

    }

}
