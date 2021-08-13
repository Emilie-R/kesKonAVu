package fr.epita.kesKonAVu.infrastructure.followUp;

import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;
import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUpRepository;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import fr.epita.kesKonAVu.domain.user.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourceFollowUpRepositoryImpl implements ResourceFollowUpRepository {
    @Autowired
    ResourceFollowUpJpaRepository resourceFollowUpJpaRepository;

    @Override
    public List<ResourceFollowUp> findByMember (Member member) {

        if(member == null) {
            return null;
        }
        return resourceFollowUpJpaRepository.findByMember(member).stream()
                .filter(r -> r.getResource().getResourceType() == ResourceTypeEnum.MOVIE)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResourceFollowUp> findByResource (Resource resource) {
        if(resource == null) {
            return null;
        }
        return resourceFollowUpJpaRepository.findByResource(resource);
    }
}