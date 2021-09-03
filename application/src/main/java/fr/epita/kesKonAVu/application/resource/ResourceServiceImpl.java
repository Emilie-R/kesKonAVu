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
    public Resource findMovieByTitle(String title) {
        return resourceRepository.findMovieByTitle(title);
    }

    @Override
    public Resource findMovieByIdResource(Long idResource) {
        return resourceRepository.findMovieByIdResource(idResource);
    }

    @Override
    public Resource findMovieByImdbId(String externalKey) {return resourceRepository.findMovieByImdbId(externalKey);
    }

}
