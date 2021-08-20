package fr.epita.kesKonAVu.domain.episodeFollowUp;

import java.util.Optional;

public interface EpisodeFollowUpRepository {

    EpisodeFollowUp save(EpisodeFollowUp episodeFollowUp);

    Optional<EpisodeFollowUp> findById(Long idEpisodeFollowUp);

}
