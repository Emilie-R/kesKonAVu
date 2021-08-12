package fr.epita.kesKonAVu.exposition.resource.rest;

import fr.epita.kesKonAVu.application.resource.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class ResourceController {

    @Autowired
    ResourceMapper resourceMapper;
    @Autowired
    ResourceService resourceService;

    @GetMapping("/id/{id}")
    public ResourceDTO getResourceById(@PathVariable("id") String resourceId) {

        ResourceMapper resourceMapper = new ResourceMapper();
        return resourceMapper.mapToDto(resourceService.findByIdResource(resourceId));

    }

    @GetMapping("/title/{title}")
    public ResourceDTO getResourceByTitle(@PathVariable("title") String title) {

        ResourceMapper resourceMapper = new ResourceMapper();
        return resourceMapper.mapToDto(resourceService.findByTitle(title));

    }

    @GetMapping("/external/{externalId}")
    public ResourceDTO getResourceByExternalReference(@PathVariable("externalId") String externalId) {

        ResourceMapper resourceMapper = new ResourceMapper();
        return resourceMapper.mapToDto(resourceService.findByExternalKey(externalId));

    }
}
