package fr.epita.kesKonAVu.exposition.resource.rest;

import fr.epita.kesKonAVu.application.resource.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apiResource")
public class ResourceController {

    @Autowired
    ResourceMapper resourceMapper;
    @Autowired
    ResourceService resourceService;

    @GetMapping(value = "/id/{id}")
    public ResourceDTO getResourceById(@PathVariable("id") String resourceId) {

        ResourceMapper resourceMapper = new ResourceMapper();
        ResourceDTO dto = resourceMapper.mapToDto(resourceService.findByIdResource(resourceId));
        return dto;

    }

    @GetMapping(value = "/title/{title}")
    public ResourceDTO getResourceByTitle(@PathVariable("title") String title) {

        ResourceMapper resourceMapper = new ResourceMapper();
        ResourceDTO dto = resourceMapper.mapToDto(resourceService.findByTitle(title));
        return dto;

    }

    @GetMapping(value = "/externalreference/{externalId}")
    public ResourceDTO getResourceByExternalReference(@PathVariable("externalId") String externalId) {

        ResourceMapper resourceMapper = new ResourceMapper();
        ResourceDTO dto = resourceMapper.mapToDto(resourceService.findByExternalKey(externalId));
        return dto;

    }
}
