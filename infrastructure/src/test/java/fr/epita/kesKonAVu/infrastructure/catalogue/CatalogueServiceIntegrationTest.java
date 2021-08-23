package fr.epita.kesKonAVu.infrastructure.catalogue;

import fr.epita.kesKonAVu.domain.catalogue.CatalogueService;
import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.resource.Episode;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import fr.epita.kesKonAVu.domain.resource.Serie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;

@DataJpaTest
public class CatalogueServiceIntegrationTest {

    @Autowired
    CatalogueService catalogueService;

    @Test
    public void findResourceByImdbId_with_existing_ImdbId_should_success() {
        // Given
        String id = "tt0464913";

        //When
        Resource resourceResult = catalogueService.findResourceByImdbId(id);

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
        Assertions.assertThrows(NotFoundException.class, () -> catalogueService.findResourceByImdbId(id));
    }

    @Test
    public void findMovieByImdbId_with_existing_ImdbId_should_success() {
        // Given
        String id = "tt0464913";

        //When
        Resource resourceResult = catalogueService.findMovieByImdbId(id);

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
        Serie resourceResult = catalogueService.findSerieByImdbId(id);

        //Then
        Assertions.assertNotNull(resourceResult);
        Assertions.assertEquals(id, resourceResult.getExternalKey());
        Assertions.assertEquals("Maguy", resourceResult.getTitle());
        Assertions.assertEquals(ResourceTypeEnum.SERIE, resourceResult.getResourceType());
        Assertions.assertEquals(7, resourceResult.getNumberOfSeasons());
    }

    @Test
    public void findAllEpisodes_all_seasons_completed_should_success() {
        // Given
        String id = "tt0944947";
        Serie serie = catalogueService.findSerieByImdbId(id);

        //When
        Set<Episode> episodes = catalogueService.findAllEpisodes(serie);

        //Then
        Assertions.assertNotNull(episodes);
        Assertions.assertEquals(73, episodes.size());
    }

    @Test
    public void findAllEpisodes_missing_seasons_should_success(){
        // Given
        String id = "tt0167643";
        Serie serie = catalogueService.findSerieByImdbId(id);

        //When
        Set<Episode> episodes = catalogueService.findAllEpisodes(serie);

        //Then
        Assertions.assertNotNull(episodes);
        Assertions.assertEquals(45, episodes.size());
    }

}
