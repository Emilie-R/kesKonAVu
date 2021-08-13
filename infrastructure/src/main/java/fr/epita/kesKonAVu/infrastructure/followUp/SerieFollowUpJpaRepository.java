package fr.epita.kesKonAVu.infrastructure.followUp;

import fr.epita.kesKonAVu.domain.resource.Serie;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieFollowUpJpaRepository {
    Serie findByTitle(String title);

    Serie findByIdResource(String idResource);

    Serie findByExternalKey (String externalKey);
}
