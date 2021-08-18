package fr.epita.kesKonAVu.domain.followUp;

import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.user.Member;

import java.util.List;
import java.util.Optional;

public interface ResourceFollowUpRepository {

    ResourceFollowUp save(ResourceFollowUp resourceFollowUp);

    Optional<ResourceFollowUp> findById(Long idFollowUp);
}
