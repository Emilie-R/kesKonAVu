package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;
import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUpRepository;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.user.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceFollowUpServiceImpl implements ResourceFollowUpService{
    @Autowired
    ResourceFollowUpRepository resourceFollowUpRepository;

    @Override
    public List<ResourceFollowUp> findByMember (Member member) {
        return resourceFollowUpRepository.findByMember(member);
    }

    @Override
    public List<ResourceFollowUp> findByResource (Resource resource) {
        return resourceFollowUpRepository.findByResource(resource);
    }
}
