package fr.epita.kesKonAVu.infrastructure.resource;

import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.resource.*;
import fr.epita.kesKonAVu.infrastructure.resource.catalogue.omdb.CatalogueApiAccessOmdbImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@DataJpaTest
public class ResourceRepositoryTest {


    /*
     *  Tous les jeux d'essais de la BDD sont injectés dans la base H2 avec le fichier import.sql
     */

    @MockBean
    CatalogueApiAccessOmdbImpl omdbCatalogueApiAccess;

    @Autowired
    ResourceRepository resourceRepository;

    ExternalKey externalKey = new ExternalKey();
    Resource resourceToSave = new Resource();

    @Test
    public void given_idResource_existing_member_findById_should_success () {
        //Given
        Long id = 1L;

        //When
        Resource result = resourceRepository.findById(1L);

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Le Grand Bleu", result.getTitle());
        Assertions.assertEquals(ResourceTypeEnum.MOVIE, result.getResourceType());
        Assertions.assertEquals("tt0095250", result.getExternalKey().getResourceId());
    }

    @Test
    public void given_idResource_unknown_member_findById_should_throw_NotFoundException () {
        //Given
        Long id = 0L;

        //When
        //Then
        Assertions.assertThrows(NotFoundException.class, () -> resourceRepository.findById(id));
    }

    @Test
    public void given_new_resource_save_should_success() {
        //Given
        externalKey.setResourceId("12345678");
        externalKey.setCatalogName(CatalogReferenceEnum.OMDBAPI);

        resourceToSave.setTitle("Le dernier métro");
        resourceToSave.setResourceType(ResourceTypeEnum.MOVIE);
        resourceToSave.setExternalKey(externalKey);

        //When
        Resource result = resourceRepository.save(resourceToSave);

        //Then
        Assertions.assertNotNull(resourceRepository.findById(result.getIdResource()));
        Assertions.assertNotNull(resourceRepository.findById(result.getIdResource())
                .getExternalKey().getId());
        Assertions.assertEquals( resourceToSave.getTitle(),
                resourceRepository.findById(result.getIdResource()).getTitle());
        Assertions.assertEquals( resourceToSave.getExternalKey().getResourceId(),
                resourceRepository.findById(result.getIdResource()).getExternalKey().getResourceId());
    }

    @Test
    public void given_existing_resource_save_should_success() {
        //Given
        Resource resourceToSave = resourceRepository.findById(1L);
        resourceToSave.setTitle("Tarzan");
        resourceToSave.getExternalKey().setResourceId("12345678");

        //When
        Resource result = resourceRepository.save(resourceToSave);

        //Then
        Assertions.assertEquals(resourceToSave.getTitle(), resourceRepository.findById(1L).getTitle());
        Assertions.assertEquals(resourceToSave.getExternalKey().getResourceId(),
                resourceRepository.findById(1L).getExternalKey().getResourceId());
        Assertions.assertEquals(resourceToSave.getIdResource(), result.getIdResource());
        Assertions.assertEquals(resourceToSave.getExternalKey().getId(), result.getExternalKey().getId());
    }

    @Test
    public void getResourceFromOmdbCatalogueByImdbId_should_call_findResourceByImdbId_Once() {
        //Given
        String imdbId = "123456";
        Mockito.when(omdbCatalogueApiAccess.findResourceByImdbId(imdbId)).thenReturn(new Resource());

        // When
        Resource result = resourceRepository.getResourceFromOmdbCatalogueByImdbId(imdbId);

        //Then
        Mockito.verify(omdbCatalogueApiAccess, Mockito.times(1)).findResourceByImdbId(imdbId);

    }


}
