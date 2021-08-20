package fr.epita.kesKonAVu.application.resource;


import fr.epita.kesKonAVu.SpringBootAppTest;
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
    public void find_another_serie_When_Title_Is_Given(){
        // GIVEN
        final Serie serie1 = new Serie();
        serie1.setTitle("Friends 2");
        serie1.setExternalKey("1234ABC");
        serie1.setIdResource(2L);
        when(serieRepositoryMock.findByTitle("Hello")).thenReturn(serie1);

        // WHEN
        final Resource createdSerie = serieService.findByTitle("Hello");

        //Then
        Assertions.assertNotNull(createdSerie);
        Assertions.assertEquals("Friends 2", createdSerie.getTitle());
    }

    @Test
    public void find_another_serie_When_ExternalKey_Is_Given(){
        // GIVEN
        final Serie serie1 = new Serie();
        serie1.setTitle("Friends 2");
        serie1.setExternalKey("1234ABC");
        serie1.setIdResource(2L);
        when(serieRepositoryMock.findByExternalKey("1234ABC")).thenReturn(serie1);

        // WHEN
        final Resource createdSerie = serieService.findByExternalKey("1234ABC");

        //Then
        Assertions.assertNotNull(createdSerie);
        Assertions.assertEquals("Friends 2", createdSerie.getTitle());
    }

    @Test
    public void find_Serie_When_Id_Is_Given(){
        // GIVEN
        final Serie serie1 = new Serie();
        serie1.setTitle("Friends 2");
        serie1.setExternalKey("1234ABC");
        serie1.setIdResource(2L);
        when(serieRepositoryMock.findByIdResource(2L)).thenReturn(serie1);

        // WHEN
        final Resource createdSerie = serieService.findByIdResource(2L);

        //Then
        Assertions.assertNotNull(createdSerie);
        Assertions.assertEquals(2, createdSerie.getIdResource());
    }

}
