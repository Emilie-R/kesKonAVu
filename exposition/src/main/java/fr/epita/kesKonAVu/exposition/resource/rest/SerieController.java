package fr.epita.kesKonAVu.exposition.resource.rest;

import fr.epita.kesKonAVu.application.resource.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/V1/serie")
public class SerieController {

    @Autowired
    SerieMapper serieMapper;
    @Autowired
    SerieService serieService;

    @GetMapping("/id/{id}")
    public SerieDTO getResourceById(@PathVariable("id") Long resourceId) {

        SerieMapper serieMapper = new SerieMapper();
        return serieMapper.mapToDto(serieService.findByIdResource(resourceId));

    }

    @GetMapping("/title/{title}")
    public SerieDTO getResourceByTitle(@PathVariable("title") String title) {

        SerieMapper serieMapper = new SerieMapper();
        return serieMapper.mapToDto(serieService.findByTitle(title));

    }

    @GetMapping("/external/{externalId}")
    public SerieDTO getResourceByExternalReference(@PathVariable("externalId") String externalId) {

        SerieMapper serieMapper = new SerieMapper();
        return serieMapper.mapToDto(serieService.findByExternalKey(externalId));

    }
}
