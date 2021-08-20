package fr.epita.kesKonAVu.application.resource;


import fr.epita.kesKonAVu.SpringBootAppTest;
import fr.epita.kesKonAVu.domain.resource.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = { SpringBootAppTest.class })
public class ResourceServiceTest {
    @Autowired
    ResourceService resourceService;
    @MockBean
    ResourceRepository resourceRepositoryMock;

    @Test
    public void find_another_movie_When_Title_Does_Not_Exists(){
        // GIVEN
        final Resource movie = new Resource();
        movie.setTitle("Friends 1");
        when(resourceRepositoryMock.findMovieByTitle("Hello")).thenReturn(movie);

        // WHEN
        final Resource createdMovie = resourceService.findByTitle("Hello");

        //Then
        assertThat(createdMovie).isNotNull();
        assertThat(createdMovie.getTitle() == "Friends 1");
    }

    @Test
    public void find_movie_When_IdIsGiven(){
        // GIVEN
        final Resource movie = new Resource();
        movie.setTitle("Friends 1");
        movie.setIdResource(1L);
        when(resourceRepositoryMock.findMovieByIdResource(3L)).thenReturn(movie);

        // WHEN
        final Resource createdMovie = resourceService.findByIdResource(3L);

        //Then
        assertThat(createdMovie).isNotNull();
        assertThat(1L == createdMovie.getIdResource());
    }

    @Test
    public void find_another_movie_When_ExternalKey_Is_Given(){
        // GIVEN
        final Resource movie = new Resource();
        movie.setTitle("Friends 1");
        movie.setExternalKey("123arc");
        when(resourceRepositoryMock.findMovieByExternalKey("Hello")).thenReturn(movie);

        // WHEN
        final Resource createdMovie = resourceService.findByExternalKey("Hello");

        //Then
        assertThat(createdMovie).isNotNull();
        assertThat(createdMovie.getExternalKey() == movie.getExternalKey());
    }
}
