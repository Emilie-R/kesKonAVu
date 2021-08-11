package fr.epita.kesKonAVu.application.resource;

import fr.epita.kesKonAVu.domain.resource.Resource;

public interface ResourceService {
    Resource findByTitle(String title);

    Resource findByIdResource(String idResource);

    Resource findByExternalKey(String externalKey);
}
