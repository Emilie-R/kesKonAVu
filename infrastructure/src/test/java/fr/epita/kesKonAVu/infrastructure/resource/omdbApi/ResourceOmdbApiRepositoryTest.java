package fr.epita.kesKonAVu.infrastructure.resource.omdbApi;

import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
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
import java.net.URISyntaxException;

@DataJpaTest
public class ResourceOmdbApiRepositoryTest {

    @MockBean
    RestTemplate restTemplate;

    @Autowired
    ResourceOmdbApiRepository resourceOmdbApiRepository;

    @Test
    public void findResourceByImdbId_with_existing_ImdbId_should_success() throws URISyntaxException {
        // Given
        String id = "tt0464913";
        URI uri = new URI("http://www.omdbapi.com/?apikey=d974f110&i=tt0464913");

        ResourceOmdb resourceOmdb = new ResourceOmdb();
        resourceOmdb.setTitle("OSS 117: Le Caire, nid d'espions");
        resourceOmdb.setImdbId(id);
        resourceOmdb.setType("movie");
        ResponseEntity<ResourceOmdb> result = new ResponseEntity<>(resourceOmdb, HttpStatus.OK);

        Mockito.when(restTemplate.getForEntity(uri, ResourceOmdb.class)).thenReturn(result);

        //When
        Resource resourceResult = resourceOmdbApiRepository.findResourceByImdbId(id);

        //Then
        Assertions.assertNotNull(resourceResult);
        Assertions.assertEquals(id, resourceResult.getExternalKey().getResourceId());
        Assertions.assertEquals("OSS 117: Le Caire, nid d'espions", resourceResult.getTitle());
        Assertions.assertEquals(ResourceTypeEnum.MOVIE, resourceResult.getResourceType());

    }

}
