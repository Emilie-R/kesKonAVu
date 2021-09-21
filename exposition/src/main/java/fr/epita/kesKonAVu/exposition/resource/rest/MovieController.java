package fr.epita.kesKonAVu.exposition.resource.rest;

import fr.epita.kesKonAVu.application.resource.MovieService;
import fr.epita.kesKonAVu.exposition.member.rest.MemberController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/movie")
public class MovieController {

    @Autowired
    MovieMapper movieMapper;

    @Autowired
    MovieService movieService;

    private static final Logger LOG = LoggerFactory.getLogger(MovieController.class);

    @GetMapping("/id/{id}")
    public MovieDTO getResourceById(@PathVariable("id") Long resourceId) {
        LOG.info("/v1/movie/id/" + resourceId);
        return movieMapper.mapToDto(movieService.findByIdResource(resourceId));

    }

    @GetMapping("/title/{title}")
    public MovieDTO getResourceByTitle(@PathVariable("title") String title) {

        return movieMapper.mapToDto(movieService.findByTitle(title));

    }

    @GetMapping("/imdb/{externalId}")
    public MovieDTO getResourceByExternalReference(@PathVariable("externalId") String externalId) {

        return movieMapper.mapToDto(movieService.findByImdbId(externalId));

    }
}
