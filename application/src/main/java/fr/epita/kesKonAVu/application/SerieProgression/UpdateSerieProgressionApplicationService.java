package fr.epita.kesKonAVu.application.SerieProgression;

import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeFollowUp;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;

import java.util.Set;

public interface UpdateSerieProgressionApplicationService {

    FollowUp updateProgressionSerie(FollowUp in);

    FollowUp saveSerieProgression (FollowUp in);

    FollowUp getEpisodeFollowUpList(Long idFollowUp);

    Set<EpisodeFollowUp> initializeEpisodeFollowUpList(FollowUp followUp);
}
