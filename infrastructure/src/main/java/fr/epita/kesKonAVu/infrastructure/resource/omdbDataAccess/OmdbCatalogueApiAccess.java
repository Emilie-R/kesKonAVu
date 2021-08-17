package fr.epita.kesKonAVu.infrastructure.resource.omdbDataAccess;

import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.common.TechnicalException;
import fr.epita.kesKonAVu.domain.resource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class OmdbCatalogueApiAccess {

    /*Chemin pour l'appel de l'API OMDB passé en paramètre */
    @Value("${param.url.omdbapi}")
    String baseURL;

    @Autowired
    ItemFromOmdbMapper itemFromOmdbMapper;

    @Autowired
    RestTemplate restTemplate;

    public Resource findResourceByImdbId(String ImdbId) {
        Resource resource = new Resource();
        try {
            URI uri = new URI(baseURL + "&i=" + ImdbId);

            /* Get Data From OMDB API */
            ResponseEntity<ItemFromOmdb> result =
                    restTemplate.getForEntity(uri, ItemFromOmdb.class);

            if (result.getStatusCode() == HttpStatus.OK) {
                if (result.getBody().getResponse().equals("True")) {
                    resource = itemFromOmdbMapper.resourceOmdbToResource(result.getBody());
                } else {
                    throw new NotFoundException("Resource non trouvée - Identifiant IMDB : " + ImdbId);
                }
            } else {
                throw new TechnicalException("Erreur lors de l'appel OMDB Api = Vérifier apiKey");
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return resource;
    }

}
