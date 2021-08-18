package fr.epita.kesKonAVu.infrastructure.catalogue;

import fr.epita.kesKonAVu.domain.catalogue.CatalogueRepository;
import fr.epita.kesKonAVu.domain.catalogue.ItemCatalogue;
import fr.epita.kesKonAVu.domain.catalogue.SerieSeasonCatalogue;
import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.common.TechnicalException;
import fr.epita.kesKonAVu.domain.resource.Serie;
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
import java.util.ArrayList;
import java.util.List;

@DataJpaTest(properties =
        "param.url.omdbapi=http://localhost/test")
public class CatalogueRepositoryTest {

    @MockBean
    RestTemplate restTemplate;
    @Autowired
    CatalogueRepository catalogueRepository;

    @Test
    public void findItemByImdbId_ItemDto_with_Response_True_should_success() {
        //Given
        String imdbId = "12345678";

        ItemCatalogueDto itemCatalogueDto = new ItemCatalogueDto();
        itemCatalogueDto.setResponse("True");
        itemCatalogueDto.setType("movie");
        itemCatalogueDto.setTitle("Le masque de zorro");

        ResponseEntity<ItemCatalogueDto> response = new ResponseEntity<>(itemCatalogueDto, HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(Mockito.any(URI.class), Mockito.eq(ItemCatalogueDto.class)))
                .thenReturn(response);

        //When
        ItemCatalogue result = catalogueRepository.findItemByImdbId(imdbId);

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(itemCatalogueDto.getTitle(), result.getTitle());
        Assertions.assertEquals(itemCatalogueDto.getType(), result.getType());

    }

    @Test
    public void findItemByImdbId_ItemDto_with_Response_False_should_throw_NotFoundException() {
        //Given
        String imdbId = "12345678";

        ItemCatalogueDto itemCatalogueDto = new ItemCatalogueDto();
        itemCatalogueDto.setResponse("False");
        itemCatalogueDto.setType("movie");
        itemCatalogueDto.setTitle("Le masque de zorro");

        ResponseEntity<ItemCatalogueDto> response = new ResponseEntity<>(itemCatalogueDto, HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(Mockito.any(URI.class), Mockito.eq(ItemCatalogueDto.class)))
                .thenReturn(response);

        //When
        //Then
        Assertions.assertThrows(NotFoundException.class, ()-> catalogueRepository.findItemByImdbId(imdbId));
    }

    @Test
    public void findItemByImdbId_with_wrong_api_key_should_throw_TechnicalException(){
        //Given
        String imdbId = "12345678";

        ItemCatalogueDto itemCatalogueDto = new ItemCatalogueDto();
        itemCatalogueDto.setResponse("False");
        itemCatalogueDto.setType("movie");
        itemCatalogueDto.setTitle("Le masque de zorro");

        ResponseEntity<ItemCatalogueDto> response = new ResponseEntity<>(itemCatalogueDto, HttpStatus.UNAUTHORIZED);
        Mockito.when(restTemplate.getForEntity(Mockito.any(URI.class), Mockito.eq(ItemCatalogueDto.class)))
                .thenReturn(response);

        //When
        //Then
        Assertions.assertThrows(TechnicalException.class, ()-> catalogueRepository.findItemByImdbId(imdbId));
    }

    @Test
    public void findAllEpisodesOfSeason_with_Response_True_should_success() {
        //Given
        SerieSeasonCatalogueDto serieSeasonCatalogueDto = new SerieSeasonCatalogueDto();
        serieSeasonCatalogueDto.setTotalSeasons("4");

        EpisodeCatalogueDto episode1 = new EpisodeCatalogueDto();
        episode1.setEpisodeNumber("1");
        episode1.setImdbId("123456789");
        EpisodeCatalogueDto episode2 = new EpisodeCatalogueDto();
        episode2.setEpisodeNumber("2");
        episode2.setImdbId("123456789");
        EpisodeCatalogueDto episode3 = new EpisodeCatalogueDto();
        episode2.setEpisodeNumber("3");
        episode2.setImdbId("123456789");
        List<EpisodeCatalogueDto> list1 = new ArrayList<>();
        list1.add(episode1);
        list1.add(episode2);
        list1.add(episode3);

        serieSeasonCatalogueDto.setSeason("1");
        serieSeasonCatalogueDto.setEpisodes(list1);
        serieSeasonCatalogueDto.setResponse("True");

        ResponseEntity<SerieSeasonCatalogueDto> response = new ResponseEntity<>(serieSeasonCatalogueDto, HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(Mockito.any(URI.class), Mockito.eq(SerieSeasonCatalogueDto.class)))
                .thenReturn(response);

        //When
        SerieSeasonCatalogue result = catalogueRepository.findAllEpisodesOfSeason("22222", 1);

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.getEpisodes().size());
        Assertions.assertEquals("4", result.getTotalSeasons());
        Assertions.assertEquals("1", result.getSeason());

    }

    @Test
    public void findAllEpisodesOfSeason_with_Response_False_should_throw_NotFoundException() {
        //Given
        SerieSeasonCatalogueDto serieSeasonCatalogueDto = new SerieSeasonCatalogueDto();
        serieSeasonCatalogueDto.setTotalSeasons("4");

        EpisodeCatalogueDto episode1 = new EpisodeCatalogueDto();
        episode1.setEpisodeNumber("1");
        episode1.setImdbId("123456789");
        EpisodeCatalogueDto episode2 = new EpisodeCatalogueDto();
        episode2.setEpisodeNumber("2");
        episode2.setImdbId("123456789");
        EpisodeCatalogueDto episode3 = new EpisodeCatalogueDto();
        episode2.setEpisodeNumber("3");
        episode2.setImdbId("123456789");
        List<EpisodeCatalogueDto> list1 = new ArrayList<>();
        list1.add(episode1);
        list1.add(episode2);
        list1.add(episode3);

        serieSeasonCatalogueDto.setSeason("1");
        serieSeasonCatalogueDto.setEpisodes(list1);
        serieSeasonCatalogueDto.setResponse("False");

        ResponseEntity<SerieSeasonCatalogueDto> response = new ResponseEntity<>(serieSeasonCatalogueDto, HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(Mockito.any(URI.class), Mockito.eq(SerieSeasonCatalogueDto.class)))
                .thenReturn(response);

        //When
        //Then
        Assertions.assertThrows(NotFoundException.class, () -> catalogueRepository.findAllEpisodesOfSeason("22222",1));
    }
    @Test
    public void findAllEpisodesOfSeason_with_wrong_api_key_should_throw_TechnicalException() {
        //Given
        SerieSeasonCatalogueDto serieSeasonCatalogueDto = new SerieSeasonCatalogueDto();
        serieSeasonCatalogueDto.setTotalSeasons("4");

        EpisodeCatalogueDto episode1 = new EpisodeCatalogueDto();
        episode1.setEpisodeNumber("1");
        episode1.setImdbId("123456789");
        EpisodeCatalogueDto episode2 = new EpisodeCatalogueDto();
        episode2.setEpisodeNumber("2");
        episode2.setImdbId("123456789");
        EpisodeCatalogueDto episode3 = new EpisodeCatalogueDto();
        episode2.setEpisodeNumber("3");
        episode2.setImdbId("123456789");
        List<EpisodeCatalogueDto> list1 = new ArrayList<>();
        list1.add(episode1);
        list1.add(episode2);
        list1.add(episode3);

        serieSeasonCatalogueDto.setSeason("1");
        serieSeasonCatalogueDto.setEpisodes(list1);
        serieSeasonCatalogueDto.setResponse("False");

        ResponseEntity<SerieSeasonCatalogueDto> response = new ResponseEntity<>(serieSeasonCatalogueDto, HttpStatus.UNAUTHORIZED);
        Mockito.when(restTemplate.getForEntity(Mockito.any(URI.class), Mockito.eq(SerieSeasonCatalogueDto.class)))
                .thenReturn(response);

        //When
        //Then
        Assertions.assertThrows(TechnicalException.class, () -> catalogueRepository.findAllEpisodesOfSeason("22222",1));
    }
}
