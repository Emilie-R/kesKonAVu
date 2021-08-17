package fr.epita.kesKonAVu.application.resource;

import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;
import fr.epita.kesKonAVu.domain.resource.Resource;

public interface ResourceService {
    Resource findByTitle(String title);

    Resource findByIdResource(Long idResource);

    Resource findByExternalKey(String externalKey);

}
