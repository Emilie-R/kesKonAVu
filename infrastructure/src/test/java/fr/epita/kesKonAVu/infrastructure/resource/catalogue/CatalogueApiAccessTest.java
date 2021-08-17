package fr.epita.kesKonAVu.infrastructure.resource.catalogue;

import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.common.TechnicalException;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import fr.epita.kesKonAVu.domain.resource.Serie;
import fr.epita.kesKonAVu.infrastructure.resource.catalogue.omdb.CatalogueApiAccessOmdbImpl;
import fr.epita.kesKonAVu.infrastructure.resource.catalogue.omdb.ItemFromOmdb;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@DataJpaTest(properties =
        "param.url.omdbapi=http://localhost/test")

public class CatalogueApiAccessTest {

    @MockBean
    RestTemplate restTemplate;
    @Autowired
    CatalogueApiAccessOmdbImpl omdbCatalogueApiAccess;

    @Test
    public void findResourceByImdbId_with_Response_True_should_success() {
        //Given
        String imdbId = "12345678";

        ItemFromOmdb itemFromOmdb = new ItemFromOmdb();
        itemFromOmdb.setResponse("True");
        itemFromOmdb.setType("movie");
        itemFromOmdb.setTitle("Le masque de zorro");

        ResponseEntity<ItemFromOmdb> response = new ResponseEntity<>(itemFromOmdb, HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(Mockito.any(URI.class), Mockito.eq(ItemFromOmdb.class)))
                .thenReturn(response);

        //When
        Resource result = omdbCatalogueApiAccess.findResourceByImdbId(imdbId);

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(itemFromOmdb.getTitle(), result.getTitle());

    }

    @Test
    public void findResourceByImdbId_with_Response_False_should_throw_NotFoundException() {
        //Given
        String imdbId = "12345678";

        ItemFromOmdb itemFromOmdb = new ItemFromOmdb();
        itemFromOmdb.setResponse("False");
        itemFromOmdb.setType("movie");
        itemFromOmdb.setTitle("Le masque de zorro");

        ResponseEntity<ItemFromOmdb> response = new ResponseEntity<>(itemFromOmdb, HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(Mockito.any(URI.class), Mockito.eq(ItemFromOmdb.class)))
                .thenReturn(response);

        //When
        //Then
        Assertions.assertThrows(NotFoundException.class, ()-> omdbCatalogueApiAccess.findResourceByImdbId(imdbId));
    }

    @Test
    public void findResourceByImdbId_with_wrong_api_key_should_throw_TechnicalException(){
        //Given
        String imdbId = "12345678";

        ItemFromOmdb itemFromOmdb = new ItemFromOmdb();
        itemFromOmdb.setResponse("False");
        itemFromOmdb.setType("movie");
        itemFromOmdb.setTitle("Le masque de zorro");

        ResponseEntity<ItemFromOmdb> response = new ResponseEntity<>(itemFromOmdb, HttpStatus.UNAUTHORIZED);
        Mockito.when(restTemplate.getForEntity(Mockito.any(URI.class), Mockito.eq(ItemFromOmdb.class)))
                .thenReturn(response);

        //When
        //Then
        Assertions.assertThrows(TechnicalException.class, ()-> omdbCatalogueApiAccess.findResourceByImdbId(imdbId));

    }

    @Test
    public void findMovieByImdbId_with_Response_True_should_success() {
        //Given
        String imdbId = "12345678";

        ItemFromOmdb itemFromOmdb = new ItemFromOmdb();
        itemFromOmdb.setResponse("True");
        itemFromOmdb.setTitle("Le masque de zorro");

        ResponseEntity<ItemFromOmdb> response = new ResponseEntity<>(itemFromOmdb, HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(Mockito.any(URI.class), Mockito.eq(ItemFromOmdb.class)))
                .thenReturn(response);

        //When
        Resource result = omdbCatalogueApiAccess.findMovieByImdbId(imdbId);

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(itemFromOmdb.getTitle(), result.getTitle());
        Assertions.assertEquals(ResourceTypeEnum.MOVIE, result.getResourceType());
    }

    @Test
    public void findSerieByImdbId_with_Response_True_should_success() {
        //Given
        String imdbId = "12345678";

        ItemFromOmdb itemFromOmdb = new ItemFromOmdb();
        itemFromOmdb.setResponse("True");
        itemFromOmdb.setTitle("Zorro");
        itemFromOmdb.setType("Guy Williams");
        itemFromOmdb.setTotalSeasons("5");

        ResponseEntity<ItemFromOmdb> response = new ResponseEntity<>(itemFromOmdb, HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(Mockito.any(URI.class), Mockito.eq(ItemFromOmdb.class)))
                .thenReturn(response);

        //When
        Serie result = omdbCatalogueApiAccess.findSerieByImdbId(imdbId);

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(itemFromOmdb.getTitle(), result.getTitle());
        Assertions.assertEquals(ResourceTypeEnum.SERIE, result.getResourceType());
        Assertions.assertEquals(itemFromOmdb.getActors(), result.getActors());
        Assertions.assertEquals(itemFromOmdb.getTotalSeasons(), result.getNumberOfSeasons().toString());

    }

    @Test
    public void findAllEpisodesOfSeason_with_Response_True_should_success() {
        //Given
        String imdbId = "12345678";
        int season = 1;

        ItemFromOmdb itemFromOmdb = new ItemFromOmdb();
        itemFromOmdb.setResponse("True");
        itemFromOmdb.setType("movie");
        itemFromOmdb.setTitle("Le masque de zorro");

        ResponseEntity<ItemFromOmdb> response = new ResponseEntity<>(itemFromOmdb, HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(Mockito.any(URI.class), Mockito.eq(ItemFromOmdb.class)))
                .thenReturn(response);

        //When
        Resource result = omdbCatalogueApiAccess.findResourceByImdbId(imdbId);

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(itemFromOmdb.getTitle(), result.getTitle());

    }

    @Test
    public void findAllEpisodesOfSeason_with_Response_False_should_throw_NotFoundException() {
        //Given
        String imdbId = "12345678";

        ItemFromOmdb itemFromOmdb = new ItemFromOmdb();
        itemFromOmdb.setResponse("False");
        itemFromOmdb.setType("movie");
        itemFromOmdb.setTitle("Le masque de zorro");

        ResponseEntity<ItemFromOmdb> response = new ResponseEntity<>(itemFromOmdb, HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(Mockito.any(URI.class), Mockito.eq(ItemFromOmdb.class)))
                .thenReturn(response);

        //When
        //Then
        Assertions.assertThrows(NotFoundException.class, ()-> omdbCatalogueApiAccess.findResourceByImdbId(imdbId));
    }

    @Test
    public void findAllEpisodesOfSeason_with_wrong_api_key_should_throw_TechnicalException(){
        //Given
        String imdbId = "12345678";

        ItemFromOmdb itemFromOmdb = new ItemFromOmdb();
        itemFromOmdb.setResponse("False");
        itemFromOmdb.setType("movie");
        itemFromOmdb.setTitle("Le masque de zorro");

        ResponseEntity<ItemFromOmdb> response = new ResponseEntity<>(itemFromOmdb, HttpStatus.UNAUTHORIZED);
        Mockito.when(restTemplate.getForEntity(Mockito.any(URI.class), Mockito.eq(ItemFromOmdb.class)))
                .thenReturn(response);

        //When
        //Then
        Assertions.assertThrows(TechnicalException.class, ()-> omdbCatalogueApiAccess.findResourceByImdbId(imdbId));

    }
}
