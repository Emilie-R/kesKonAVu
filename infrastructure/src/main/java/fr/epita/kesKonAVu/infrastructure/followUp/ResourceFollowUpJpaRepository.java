package fr.epita.kesKonAVu.infrastructure.followUp;

import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceFollowUpJpaRepository extends JpaRepository<ResourceFollowUp, Long> {

    List<ResourceFollowUp> findByMember(Member member);

    List<ResourceFollowUp> findByResource(Resource resource);

}
