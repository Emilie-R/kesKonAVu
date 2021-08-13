package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.followUp.SerieFollowUp;
import fr.epita.kesKonAVu.domain.followUp.SerieFollowUpRepository;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.user.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieFollowUpServiceImpl implements SerieFollowUpService{
    @Autowired
    SerieFollowUpRepository serieFollowUpRepository;

    @Override
    public List<SerieFollowUp> findByMember (Member member) {
        return serieFollowUpRepository.findByMember(member);
    }

    @Override
    public List<SerieFollowUp> findByResource (Resource resource) {
        return serieFollowUpRepository.findByResource(resource);
    }
}
