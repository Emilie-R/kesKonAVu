package fr.epita.kesKonAVu.infrastructure.resource.catalogue.omdb;

import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.common.TechnicalException;
import fr.epita.kesKonAVu.domain.resource.Episode;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.Serie;
import fr.epita.kesKonAVu.infrastructure.resource.catalogue.CatalogueApiAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

@Component
public class CatalogueApiAccessOmdbImpl implements CatalogueApiAccess {

    /*Chemin pour l'appel de l'API OMDB passé en paramètre */
    @Value("${param.url.omdbapi}")
    String baseURL;

    @Autowired
    OmdbObjectMapper omdbObjectMapper;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Resource findResourceByImdbId(String imdbId) {
        Resource resource = omdbObjectMapper.itemOmdbToResource(findItemByImdbId(imdbId));
        return resource;
    }

    @Override
    public Resource findMovieByImdbId(String imdbId) {
        Resource movie = omdbObjectMapper.itemOmdbToMovie(findItemByImdbId(imdbId));
        return movie;
    }

    @Override
    public Serie findSerieByImdbId(String imdbId) {
        Serie serie = omdbObjectMapper.itemOmdbToSerie(findItemByImdbId(imdbId));
        return serie;
    }

    @Override
    public Set<Episode> findAllEpisodes(Serie serie) {
        return null;
    }

    /**
     * allow to get an item from OMDB Api.
     * Movie, Serie and Episode are registred with an ImdbId in OMDBAPI = Item of the catalogue
     * @param ImdbId the key to access the Catalogue
     * @return Item from Catalogue : Movie, Episode or Serie
     */
    public ItemFromOmdb findItemByImdbId(final String ImdbId) {
        ItemFromOmdb itemFromOmdb = new ItemFromOmdb();
        try {
            URI uri = new URI(baseURL + "&i=" + ImdbId);

            /* Get Data From OMDB API */
            ResponseEntity<ItemFromOmdb> result =
                    restTemplate.getForEntity(uri, ItemFromOmdb.class);

            if (result.getStatusCode() == HttpStatus.OK) {
                if (result.getBody().getResponse().equals("True")) {
                    itemFromOmdb = result.getBody();
                } else {
                    throw new NotFoundException("Resource non trouvée - Identifiant IMDB : " + ImdbId);
                }
            } else {
                throw new TechnicalException("Erreur lors de l'appel OMDB Api = Vérifier apiKey");
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return itemFromOmdb;
    }

    /**
     * allow to get the list of Episodes of a Season from OMDB API
     * @param imdbId the key of the Serie to access the Catalogue
     * @param seasonNumber the number of the season
     * @return List of all episodes for the season from Imdb Catalogue
     */
    public ListOfEpisodeFromOmdb findAllEpisodesOfSeason(String imdbId, int seasonNumber) {
        ListOfEpisodeFromOmdb listOfEpisodeFromOmdb = new ListOfEpisodeFromOmdb();
        try {
            URI uri = new URI(baseURL + "&i=" + imdbId + "&Season=" + seasonNumber);

            /* Get Data From OMDB API */
            ResponseEntity<ListOfEpisodeFromOmdb> result =
                    restTemplate.getForEntity(uri, ListOfEpisodeFromOmdb.class);

            if (result.getStatusCode() == HttpStatus.OK) {
                if (result.getBody().getResponse().equals("True")) {
                    listOfEpisodeFromOmdb = result.getBody();
                } else {
                    throw new NotFoundException("Saison inexistante - Identifiant Serie : " + imdbId + " saison : " + seasonNumber);
                }
            } else {
                throw new TechnicalException("Erreur lors de l'appel OMDB Api = Vérifier apiKey");
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return listOfEpisodeFromOmdb;
    }

}
