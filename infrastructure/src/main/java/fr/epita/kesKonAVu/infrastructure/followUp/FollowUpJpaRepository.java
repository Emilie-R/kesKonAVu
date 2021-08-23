package fr.epita.kesKonAVu.infrastructure.followUp;

import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowUpJpaRepository extends JpaRepository<FollowUp, Long> {

    FollowUp findByResourceAndMember(Resource resource, Member member);

    @Query("select f from FollowUp f left join fetch f.episodeFollowUps where f.id = :id")
    FollowUp findByIdWithAllResourceFollowUps (@Param("id") Long idFollowUp);
}
