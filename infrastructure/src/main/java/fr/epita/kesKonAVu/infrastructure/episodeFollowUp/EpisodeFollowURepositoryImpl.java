package fr.epita.kesKonAVu.infrastructure.episodeFollowUp;

import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeFollowUp;
import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeFollowUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EpisodeFollowURepositoryImpl implements EpisodeFollowUpRepository {
    @Autowired
    EpisodeFollowUpJpaRepository efuJpa;

    @Override
    public EpisodeFollowUp save (EpisodeFollowUp episodeFollowUp) {
        return efuJpa.save(episodeFollowUp);
    }

    @Override
    public Optional<EpisodeFollowUp> findById (Long idEpisodeFollowUp) {
        return efuJpa.findById(idEpisodeFollowUp);
    }
}
