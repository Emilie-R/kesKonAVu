package fr.epita.kesKonAVu.infrastructure.followUp;

import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;
import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUpRepository;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.user.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResourceFollowUpRepositoryImpl implements ResourceFollowUpRepository {
    @Autowired
    ResourceFollowUpJpaRepository resourceFollowUpJpaRepository;

    @Override
    public ResourceFollowUp save(ResourceFollowUp resourceFollowUp) {
        return resourceFollowUpJpaRepository.save(resourceFollowUp);
    }

    @Override
    public Optional<ResourceFollowUp> findById(Long idFollowUp) {
        if(! resourceFollowUpJpaRepository.findById(idFollowUp).isPresent()) {
            throw new NotFoundException("ResourceFollowUp with idFollowUp " + idFollowUp + "not found in database. PLease Check parameters.");
        }
        return resourceFollowUpJpaRepository.findById(idFollowUp);
    }

    @Override
    public ResourceFollowUp findByResourceAndMember(Resource resource, Member member) {
        return resourceFollowUpJpaRepository.findByResourceAndMember(resource, member);
    }
}
