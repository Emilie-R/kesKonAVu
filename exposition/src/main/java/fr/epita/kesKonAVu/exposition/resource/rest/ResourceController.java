package fr.epita.kesKonAVu.exposition.resource.rest;

import fr.epita.kesKonAVu.application.resource.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/V1/movie")
public class ResourceController {

    @Autowired
    ResourceMapper resourceMapper;
    @Autowired
    ResourceService resourceService;

    @GetMapping("/id/{id}")
    public ResourceDTO getResourceById(@PathVariable("id") Long resourceId) {

        return resourceMapper.mapToDto(resourceService.findMovieByIdResource(resourceId));

    }

    @GetMapping("/title/{title}")
    public ResourceDTO getResourceByTitle(@PathVariable("title") String title) {

        return resourceMapper.mapToDto(resourceService.findMovieByTitle(title));

    }

    @GetMapping("/imdb/{externalId}")
    public ResourceDTO getResourceByExternalReference(@PathVariable("externalId") String externalId) {

        return resourceMapper.mapToDto(resourceService.findMovieByImdbId(externalId));

    }
}
