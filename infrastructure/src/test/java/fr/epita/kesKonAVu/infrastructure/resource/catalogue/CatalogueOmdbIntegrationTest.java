package fr.epita.kesKonAVu.infrastructure.resource.catalogue;

import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import fr.epita.kesKonAVu.domain.resource.Serie;
import fr.epita.kesKonAVu.infrastructure.resource.catalogue.omdb.CatalogueApiAccessOmdbImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest(properties =
        "param.url.omdbapi=http://www.omdbapi.com/?apikey=d974f110")
public class CatalogueOmdbIntegrationTest {

    @Autowired
    CatalogueApiAccess omdbCatalogueApiAccess;

    @Test
    public void findResourceByImdbId_with_existing_ImdbId_should_success() {
        // Given
        String id = "tt0464913";

        //When
        Resource resourceResult = omdbCatalogueApiAccess.findResourceByImdbId(id);

        //Then
        Assertions.assertNotNull(resourceResult);
        Assertions.assertEquals(id, resourceResult.getExternalKey());
        Assertions.assertEquals("OSS 117: Le Caire, nid d'espions", resourceResult.getTitle());
        Assertions.assertEquals(ResourceTypeEnum.MOVIE, resourceResult.getResourceType());
    }

    @Test
    public void findResourceByImdbId_with_unknown_ImdbId_should_throw_NotFoundException() {
        // Given
        String id = "12345678";

        //When
        //Then
        Assertions.assertThrows(NotFoundException.class, () -> omdbCatalogueApiAccess.findResourceByImdbId(id));
    }

    @Test
    public void findMovieByImdbId_with_existing_ImdbId_should_success() {
        // Given
        String id = "tt0464913";

        //When
        Resource resourceResult = omdbCatalogueApiAccess.findMovieByImdbId(id);

        //Then
        Assertions.assertNotNull(resourceResult);
        Assertions.assertEquals(id, resourceResult.getExternalKey());
        Assertions.assertEquals("OSS 117: Le Caire, nid d'espions", resourceResult.getTitle());
        Assertions.assertEquals(ResourceTypeEnum.MOVIE, resourceResult.getResourceType());
    }

    @Test
    public void findSerieByImdbId_with_existing_ImdbId_should_success() {
        // Given
        String id = "tt0167643";

        //When
        Serie resourceResult = omdbCatalogueApiAccess.findSerieByImdbId(id);

        //Then
        Assertions.assertNotNull(resourceResult);
        Assertions.assertEquals(id, resourceResult.getExternalKey());
        Assertions.assertEquals("Maguy", resourceResult.getTitle());
        Assertions.assertEquals(ResourceTypeEnum.SERIE, resourceResult.getResourceType());
        Assertions.assertEquals(7, resourceResult.getNumberOfSeasons());
    }

}
