package fr.epita.kesKonAVu.application.resource;

import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService{
    @Autowired
    ResourceRepository resourceRepository;

    @Override
    public Resource findByTitle (String title) {
        return resourceRepository.findByTitle(title);
    }

    @Override
    public Resource findByIdResource (String idResource) {
        return resourceRepository.findByIdResource(idResource);
    }

    @Override
    public Resource findByExternalKey (String externalKey) {
        return resourceRepository.findByExternalKey(externalKey);
    }
}