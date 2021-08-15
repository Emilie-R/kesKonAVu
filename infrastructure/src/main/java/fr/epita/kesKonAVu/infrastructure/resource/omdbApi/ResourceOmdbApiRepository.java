package fr.epita.kesKonAVu.infrastructure.resource.omdbApi;

import fr.epita.kesKonAVu.domain.resource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class ResourceOmdbApiRepository {

    @Value("${param.url.omdbapi}")
    private final static String baseURL = "http://www.omdbapi.com/?apikey=d974f110";

    @Autowired
    ResourceOmdbMapper resourceMapper;

    @Autowired
    private RestTemplate restTemplate;

    public Resource findResourceByImdbId(String ImdbId) {
        System.out.println(baseURL);
        try {
            URI uri = new URI(baseURL + "&i=" + ImdbId);
            ResponseEntity<ResourceOmdb> result = restTemplate.getForEntity(uri, ResourceOmdb.class);

            Resource resource = resourceMapper.ResourceOmdbToResource(result.getBody());
            return resource;

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }

}
