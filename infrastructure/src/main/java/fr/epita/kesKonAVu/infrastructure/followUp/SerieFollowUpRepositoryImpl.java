package fr.epita.kesKonAVu.infrastructure.followUp;

import fr.epita.kesKonAVu.domain.followUp.SerieFollowUp;
import fr.epita.kesKonAVu.domain.followUp.SerieFollowUpRepository;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import fr.epita.kesKonAVu.domain.user.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieFollowUpRepositoryImpl implements SerieFollowUpRepository {
    @Autowired
    SerieFollowUpJpaRepository serieFollowUpJpaRepository;

    @Override
    public List<SerieFollowUp> findByMember (Member member) {
        if(member == null) {
            return null;
        }
        return serieFollowUpJpaRepository.findByMember(member).stream()
                .filter(r -> r.getResource().getResourceType() == ResourceTypeEnum.SERIE)
                .collect(Collectors.toList());
    }

    @Override
    public List<SerieFollowUp> findByResource (Resource resource) {
        if(resource == null) {
            return null;
        }
        return serieFollowUpJpaRepository.findByResource(resource);
    }
}
