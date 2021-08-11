package fr.epita.kesKonAVu.infrastructure.resource;

import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceRepositoryImpl implements ResourceRepository {

    @Autowired
    ResourceJpaRepository resourceJpaRepository;

    @Override
    public Resource findByTitle (String title) {
        Resource resourceOut = new Resource();
        Resource result = resourceJpaRepository.findByTitle(title);
        if (result != null){
            resourceOut = result;
        } else {
            throw new NotFoundException("Resource dont le titre est \"" + title + "\" Not found");
        }

        return resourceOut;
    }

    @Override
    public Resource findByIdResource (String idResource) {

        Resource resourceOut = new Resource();
        Resource result = resourceJpaRepository.findByIdResource(idResource);
        if (result != null){
            resourceOut = result;
        } else {
            throw new NotFoundException("Resource n° " + idResource + " Not found");
        }

        return resourceOut;
    }

    @Override
    public Resource findByExternalKey (String externalKey) {
        Resource resourceOut = new Resource();
        Resource result = resourceJpaRepository.findByExternalKey(externalKey);
        if (result != null){
            resourceOut = result;
        } else {
            throw new NotFoundException("Resource with the catalog id n° " + externalKey + " Not found");
        }

        return resourceOut;
    }
}
