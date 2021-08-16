package fr.epita.kesKonAVu.infrastructure.resource.omdbDataAccess;

import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.common.TechnicalException;
import fr.epita.kesKonAVu.domain.resource.Resource;
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

public class OmdbCatalogueApiAccessTest {

    @MockBean
    RestTemplate restTemplate;
    @Autowired
    OmdbCatalogueApiAccess omdbCatalogueApiAccess;

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
}
