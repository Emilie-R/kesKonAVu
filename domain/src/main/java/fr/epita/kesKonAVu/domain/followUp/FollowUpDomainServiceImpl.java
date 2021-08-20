package fr.epita.kesKonAVu.domain.followUp;

import fr.epita.kesKonAVu.domain.common.BusinessException;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import org.springframework.stereotype.Service;

@Service
public class FollowUpDomainServiceImpl implements FollowUpDomainService {

    @Override
    public Float calculateProgressionForASerie (FollowUp in) {

        if (in.getResource() == null || in.getResource().getResourceType() == ResourceTypeEnum.MOVIE){
            throw new BusinessException("Must be a Serie to calculate a progession !");
        }
        if (in.getEpisodeFollowUps() == null) {
            throw new BusinessException("There's no episode followed for this serie !");
        }

            Long episodesViewed = in.getEpisodeFollowUps().stream()
                    .filter(e -> e.getStatus() == StatusEnum.VU)
                    .count();
        Long episodesUnviewed = in.getEpisodeFollowUps().stream()
                .filter(e -> e.getStatus() == StatusEnum.AVOIR)
                .count();
        Long total = episodesUnviewed+episodesViewed;
        Float result = 0F;
        if (total > 0F ) {
                result = Float.valueOf(100 * episodesViewed / total);
            }
        return result;

    }
}
