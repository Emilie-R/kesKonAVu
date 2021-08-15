package fr.epita.kesKonAVu.infrastructure.resource;

import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.ResourceRepository;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import fr.epita.kesKonAVu.infrastructure.resource.omdbApi.ResourceOmdbApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceRepositoryImpl implements ResourceRepository {

    @Autowired
    ResourceJpaRepository resourceJpaRepository;

    @Autowired
    ResourceOmdbApiRepository resourceOmdbApiRepository;

    @Override
    public Resource findByTitle (String title) {
        Resource resourceOut = new Resource();
        Resource result = resourceJpaRepository.findByTitleAndResourceType(title, ResourceTypeEnum.MOVIE);
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
        Resource result = resourceJpaRepository.findByIdResourceAndResourceType(idResource,ResourceTypeEnum.MOVIE);
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
        Resource result = resourceJpaRepository.findByExternalKeyAndResourceType(externalKey,ResourceTypeEnum.MOVIE);
        if (result != null){
            resourceOut = result;
        } else {
            throw new NotFoundException("Resource with the catalog id n° " + externalKey + " Not found");
        }

        return resourceOut;
    }

    @Override
    public Resource save(Resource resource) {
        return resourceJpaRepository.save(resource);
    }

    @Override
    public Resource findResourceWithOmdbApi(String externalKey) {
        return resourceOmdbApiRepository.findResourceByImdbId(externalKey);
    }


}
