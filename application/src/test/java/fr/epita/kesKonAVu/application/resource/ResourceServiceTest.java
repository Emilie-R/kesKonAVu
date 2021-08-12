package fr.epita.kesKonAVu.application.resource;


import fr.epita.kesKonAVu.application.SpringBootAppTest;
import fr.epita.kesKonAVu.domain.resource.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringBootAppTest.class })
public class ResourceServiceTest {
    @Autowired
    ResourceService resourceService;
    @MockBean
    ResourceRepository resourceRepositoryMock;

    @Test
    public void find_another_movie_When_SendedMovie_Does_Not_Exists(){
        // GIVEN
        final Resource movie = new Resource();
        movie.setTitle("Friends 1");
        movie.setExternalKey(new ExternalKey());
        when(resourceRepositoryMock.findByTitle("Friends 1")).thenReturn(movie);

        // WHEN
        final Resource createdMovie = resourceService.findByTitle("Friends 1");

        //Then
        assertThat(createdMovie).isNotNull();
        assertThat(createdMovie.getTitle() == "Friends 1");
    }

}