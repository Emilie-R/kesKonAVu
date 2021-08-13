package fr.epita.kesKonAVu.infrastructure.followUp;

import fr.epita.kesKonAVu.domain.followUp.SerieFollowUp;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieFollowUpJpaRepository extends JpaRepository<SerieFollowUp, Long> {

    List<SerieFollowUp> findByMember(Member member);

    List<SerieFollowUp> findByResource(Resource resource);
}
