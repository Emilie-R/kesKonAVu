package fr.epita.kesKonAVu.infrastructure.episodeFollowUp;

import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeFollowUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeFollowUpJpaRepository extends JpaRepository<EpisodeFollowUp,Long> {
}
