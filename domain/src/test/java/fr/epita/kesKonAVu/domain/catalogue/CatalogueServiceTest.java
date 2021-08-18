package fr.epita.kesKonAVu.domain.catalogue;

import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.resource.Episode;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import fr.epita.kesKonAVu.domain.resource.Serie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class CatalogueServiceTest {

    @MockBean
    CatalogueRepository catalogueRepository;

    @Autowired
    CatalogueService catalogueService;

    @Test
    public void findMovieByImdbId_exiting_item_should_success() {
        //Given
        String imdbId = "12345678";

        ItemCatalogue itemCatalogue = new ItemCatalogue();
        itemCatalogue.setTitle("Le masque de zorro");
        itemCatalogue.setImdbId(imdbId);

        Mockito.when(catalogueRepository.findItemByImdbId(imdbId)).thenReturn(itemCatalogue);

        //When
        Resource result = catalogueService.findMovieByImdbId(imdbId);

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(itemCatalogue.getTitle(), result.getTitle());
        Assertions.assertEquals(ResourceTypeEnum.MOVIE, result.getResourceType());
    }

    @Test
    public void findSerieByImdbId_existing_item_should_success() {
        //Given
        String imdbId = "12345678";

        ItemCatalogue itemCatalogue = new ItemCatalogue();
        itemCatalogue.setTitle("Zorro");
        itemCatalogue.setType("Guy Williams");
        itemCatalogue.setTotalSeasons("5");

        Mockito.when(catalogueRepository.findItemByImdbId(imdbId)).thenReturn(itemCatalogue);

        //When
        Serie result = catalogueService.findSerieByImdbId(imdbId);

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(itemCatalogue.getTitle(), result.getTitle());
        Assertions.assertEquals(ResourceTypeEnum.SERIE, result.getResourceType());
        Assertions.assertEquals(itemCatalogue.getActors(), result.getActors());
        Assertions.assertEquals(itemCatalogue.getTotalSeasons(), result.getNumberOfSeasons().toString());
    }

    @Test
    public void findAllEpisodes_existing_list_should_success() {
        //Given
        Serie serie = new Serie();
        serie.setNumberOfSeasons(4);
        serie.setExternalKey("22222");

        EpisodeCatalogue episode1 = new EpisodeCatalogue();
        episode1.setEpisodeNumber("1");
        episode1.setImdbId("123456789");
        EpisodeCatalogue episode2 = new EpisodeCatalogue();
        episode2.setEpisodeNumber("2");
        episode2.setImdbId("123456789");
        EpisodeCatalogue episode3 = new EpisodeCatalogue();
        episode2.setEpisodeNumber("3");
        episode2.setImdbId("123456789");
        List<EpisodeCatalogue> list1 = new ArrayList<>();
        list1.add(episode1);
        list1.add(episode2);
        list1.add(episode3);

        SerieSeasonCatalogue listSeason1 = new SerieSeasonCatalogue();
        listSeason1.setSeason("1");
        listSeason1.setEpisodes(list1);

        SerieSeasonCatalogue listSeason4 = new SerieSeasonCatalogue();
        listSeason4.setSeason("4");
        listSeason4.setEpisodes(list1);

        Mockito.when(catalogueRepository.findAllEpisodesOfSeason(serie.getExternalKey(), 1))
                .thenReturn(listSeason1);
        Mockito.when(catalogueRepository.findAllEpisodesOfSeason(serie.getExternalKey(), 4))
                .thenReturn(listSeason4);
        Mockito.when(catalogueRepository.findAllEpisodesOfSeason(serie.getExternalKey(), 2))
                .thenThrow(NotFoundException.class);
        Mockito.when(catalogueRepository.findAllEpisodesOfSeason(serie.getExternalKey(), 3))
                .thenThrow(NotFoundException.class);

        //When
        Set<Episode> result = catalogueService.findAllEpisodes(serie);

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(6, result.size());

    }
}
