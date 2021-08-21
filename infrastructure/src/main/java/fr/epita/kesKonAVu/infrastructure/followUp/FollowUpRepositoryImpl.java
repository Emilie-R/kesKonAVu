package fr.epita.kesKonAVu.infrastructure.followUp;

import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.followUp.FollowUpRepository;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.user.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FollowUpRepositoryImpl implements FollowUpRepository {

    @Autowired
    FollowUpJpaRepository followUpJpaRepository;

    @Override
    public FollowUp save(FollowUp resourceFollowUp) {
        return followUpJpaRepository.save(resourceFollowUp);
    }

    @Override
    public Optional<FollowUp> findById(Long idFollowUp) {
        if(! followUpJpaRepository.findById(idFollowUp).isPresent()) {
            throw new NotFoundException("FollowUp with idFollowUp " + idFollowUp + "not found in database. PLease Check parameters.");
        }
        return followUpJpaRepository.findById(idFollowUp);
    }

    @Override
    public FollowUp findByResourceAndMember(Resource resource, Member member) {
        return followUpJpaRepository.findByResourceAndMember(resource, member);
    }

    @Override
    public void deleteById(Long idFollowUp) {
        resourceFollowUpJpaRepository.deleteById(idFollowUp);
    }
}
