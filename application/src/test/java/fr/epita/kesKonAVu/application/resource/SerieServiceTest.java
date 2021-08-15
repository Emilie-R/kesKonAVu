package fr.epita.kesKonAVu.application.resource;


import fr.epita.kesKonAVu.application.SpringBootAppTest;
import fr.epita.kesKonAVu.domain.resource.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = { SpringBootAppTest.class })
public class SerieServiceTest {
    @Autowired
    SerieService serieService;
    @MockBean
    SerieRepository serieRepositoryMock;

    @Test
    public void find_another_serie_When_SendedSerie_Does_Not_Exists(){
        // GIVEN
        final Serie serie1 = new Serie();
        serie1.setTitle("Friends 2");
        ExternalKey externalKey = new ExternalKey();
        externalKey.setResourceId("1234");
        serie1.setExternalKey(externalKey);
        when(serieRepositoryMock.findByExternalKey("1234")).thenReturn(serie1);

        // WHEN
        final Resource createdSerie = serieService.findByExternalKey("1234");

        //Then
        Assertions.assertNotNull(createdSerie);
        Assertions.assertEquals("Friends 2", createdSerie.getTitle());
    }

}
