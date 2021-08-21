package fr.epita.kesKonAVu.infrastructure.catalogue;

import fr.epita.kesKonAVu.domain.catalogue.CatalogueRepository;
import fr.epita.kesKonAVu.domain.catalogue.ItemCatalogue;
import fr.epita.kesKonAVu.domain.catalogue.SerieSeasonCatalogue;
import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.common.TechnicalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class CatalogueRepositoryImpl implements CatalogueRepository {

    /* Chemin pour l'appel de l'API OMDB passé en paramètre */
    @Value("${keskonavu.catalogue.omdb.url}")
    String baseURL;

    /* Valeur de la clef pour accéder au catalogue */
    @Value("${keskonavu.catalogue.omdb.apikey}")
    String apikey;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CatalogueDtoMapper catalogueDTOMapper;

    /**
     * allow to get an item from OMDB Api.
     * Movie, Serie and Episode are registred with an ImdbId in OMDBAPI = Item of the catalogue
     *
     * @param ImdbId the key to access the Catalogue
     * @return Item from Catalogue : Movie, Episode or Serie
     */
    public ItemCatalogue findItemByImdbId(final String ImdbId) {
        ItemCatalogueDto itemCatalogueDTO = new ItemCatalogueDto();
        try {
            URI uri = new URI(baseURL + apikey + "&i=" + ImdbId);

            /* Get Data From OMDB API */
            ResponseEntity<ItemCatalogueDto> result =
                    restTemplate.getForEntity(uri, ItemCatalogueDto.class);

            if (result.getStatusCode() == HttpStatus.OK) {
                if (result.getBody() != null
                        && result.getBody().getResponse().equals("True")) {
                    itemCatalogueDTO = result.getBody();
                } else {
                    throw new NotFoundException("Resource non trouvée - Identifiant IMDB : " + ImdbId);
                }
            } else {
                throw new TechnicalException("Erreur lors de l'appel OMDB Api = Vérifier apiKey");
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return catalogueDTOMapper.DtoToItemCatalogue(itemCatalogueDTO);
    }

    /**
     * allow to get the list of Episodes of a Season from OMDB API
     *
     * @param imdbId the key of the Serie to access the Catalogue
     * @param seasonNumber the number of the season
     * @return List of all episodes for the season from Imdb Catalogue
     */
    public SerieSeasonCatalogue findAllEpisodesOfSeason(String imdbId, int seasonNumber) {
        SerieSeasonCatalogueDto serieSeasonCatalogueDTO = new SerieSeasonCatalogueDto();
        try {
            URI uri = new URI(baseURL + apikey + "&i=" + imdbId + "&Season=" + seasonNumber);

            /* Get Data From OMDB API */
            ResponseEntity<SerieSeasonCatalogueDto> result =
                    restTemplate.getForEntity(uri, SerieSeasonCatalogueDto.class);

            if (result.getStatusCode() == HttpStatus.OK) {
                if ( result.getBody() != null
                        && result.getBody().getResponse().equals("True")) {
                    serieSeasonCatalogueDTO = result.getBody();
                } else {
                    throw new NotFoundException("Saison inexistante - Identifiant Serie : " + imdbId + " saison : " + seasonNumber);
                }
            } else {
                throw new TechnicalException("Erreur lors de l'appel OMDB Api = Vérifier apiKey");
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return catalogueDTOMapper.dtoToSerieSeasonCatalogue(serieSeasonCatalogueDTO);
    }

}
