package fr.epita.kesKonAVu.infrastructure.resource;

import fr.epita.kesKonAVu.domain.resource.Episode;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import fr.epita.kesKonAVu.domain.resource.Serie;
import fr.epita.kesKonAVu.domain.resource.SerieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.Set;

@DataJpaTest
public class SerieRepositoryTest {

    @Autowired
    SerieRepository serieRepository;

    @Test
    public void given_new_serie_with_episodes_save_should_success ( ) {
        //Given
        Serie serie = new Serie();
        serie.setTitle("Urgences");
        serie.setResourceType(ResourceTypeEnum.SERIE);
        serie.setImdbId("1234567");
        serie.setNumberOfSeasons(2);

        Episode episode1 = new Episode();
        episode1.setSeasonNumber(1);
        episode1.setNumber(1);
        episode1.setTitle("E1");
        Episode episode2 = new Episode();
        episode2.setSeasonNumber(1);
        episode2.setNumber(2);
        episode2.setTitle("E2");
        Episode episode3 = new Episode();
        episode3.setSeasonNumber(2);
        episode3.setNumber(3);
        episode3.setTitle("E3");
        Set<Episode> episodes = new HashSet<>();
        episodes.add(episode1);
        episodes.add(episode2);
        episodes.add(episode3);
        serie.setEpisodes(episodes);

        //When
        Serie result = serieRepository.save(serie);

        //Then
        Assertions.assertNotNull(result.getIdResource());
        Assertions.assertNotNull(serieRepository.findByIdResource(result.getIdResource()));
        Assertions.assertEquals(serie.getNumberOfSeasons(), result.getNumberOfSeasons());
        Assertions.assertEquals(serie.getEpisodes().size(), serie.getEpisodes().size());
    }
    @Test
    public void givenSerieWithItsEpisodesOK() {
        //Given
        Serie serie = new Serie();
        serie.setTitle("Urgences");
        serie.setResourceType(ResourceTypeEnum.SERIE);
        serie.setImdbId("1234567");
        serie.setNumberOfSeasons(2);

        Episode episode1 = new Episode();
        episode1.setSeasonNumber(1);
        episode1.setNumber(1);
        episode1.setTitle("E1");
        Episode episode2 = new Episode();
        episode2.setSeasonNumber(1);
        episode2.setNumber(2);
        episode2.setTitle("E2");
        Episode episode3 = new Episode();
        episode3.setSeasonNumber(2);
        episode3.setNumber(3);
        episode3.setTitle("E3");
        Set<Episode> episodes = new HashSet<>();
        episodes.add(episode1);
        episodes.add(episode2);
        episodes.add(episode3);
        serie.setEpisodes(episodes);

        //When
        Serie intermediaire = serieRepository.save(serie);
        Serie result = serieRepository.findByIdWithAllEpisodes(intermediaire.getIdResource());
        //Then
        Assertions.assertEquals(3, result.getEpisodes().size()); // because CASCADE = ALL for Serie and Episodes
    }

}
