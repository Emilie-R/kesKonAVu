package fr.epita.kesKonAVu.infrastructure.resource.omdbDataAccess;

import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.net.URISyntaxException;

@DataJpaTest(properties =
        "param.url.omdbapi=http://www.omdbapi.com/?apikey=d974f110")
public class OmdbDataAccessIntegrationTest {

    @Autowired
    OmdbCatalogueApiAccess omdbCatalogueApiAccess;

    @Test
    public void findResourceByImdbId_with_existing_ImdbId_should_success() {
        // Given
        String id = "tt0464913";

        //When
        Resource resourceResult = omdbCatalogueApiAccess.findResourceByImdbId(id);

        //Then
        Assertions.assertNotNull(resourceResult);
        Assertions.assertEquals(id, resourceResult.getExternalKey().getResourceId());
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

}
