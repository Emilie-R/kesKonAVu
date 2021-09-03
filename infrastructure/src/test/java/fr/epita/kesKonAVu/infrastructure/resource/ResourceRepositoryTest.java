package fr.epita.kesKonAVu.infrastructure.resource;

import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.resource.CatalogReferenceEnum;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.ResourceRepository;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ResourceRepositoryTest {


    /*
     *  Tous les jeux d'essais de la BDD sont injectés dans la base H2 avec le fichier import.sql
     */


    @Autowired
    ResourceRepository resourceRepository;

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
        Assertions.assertEquals("tt0095250", result.getImdbId());
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
        resourceToSave.setTitle("Le dernier métro");
        resourceToSave.setResourceType(ResourceTypeEnum.MOVIE);
        resourceToSave.setImdbId("12345678");
        resourceToSave.setResourceDataSource(CatalogReferenceEnum.OMDBAPI);

        //When
        Resource result = resourceRepository.save(resourceToSave);

        //Then
        Assertions.assertNotNull(resourceRepository.findById(result.getIdResource()));
        Assertions.assertEquals( resourceToSave.getTitle(),
                resourceRepository.findById(result.getIdResource()).getTitle());
        Assertions.assertEquals( resourceToSave.getImdbId(),
                resourceRepository.findById(result.getIdResource()).getImdbId());
    }

    @Test
    public void given_existing_resource_save_should_success() {
        //Given
        Resource resourceToSave = resourceRepository.findById(1L);
        resourceToSave.setTitle("Tarzan");
        resourceToSave.setImdbId("12345678");

        //When
        Resource result = resourceRepository.save(resourceToSave);

        //Then
        Assertions.assertEquals(resourceToSave.getTitle(), resourceRepository.findById(1L).getTitle());
        Assertions.assertEquals(resourceToSave.getImdbId(),
                resourceRepository.findById(1L).getImdbId());
        Assertions.assertEquals(resourceToSave.getIdResource(), result.getIdResource());
        Assertions.assertEquals(resourceToSave.getImdbId(), result.getImdbId());
    }

    @Test
    public void ShouldFindByTitleWhenResourceIsGiven() {
        //Given
        resourceToSave.setTitle("Le dernier métro");
        resourceToSave.setResourceType(ResourceTypeEnum.MOVIE);
        resourceToSave.setImdbId("12345678");
        resourceToSave.setResourceDataSource(CatalogReferenceEnum.OMDBAPI);

        //When
        Resource result = resourceRepository.save(resourceToSave);

        //Then
        Resource retrieved = resourceRepository.findMovieByTitle(result.getTitle());
        Assertions.assertEquals( resourceToSave.getTitle(), retrieved.getTitle());
    }
    @Test
    public void ShouldFindByExternalkeyWhenResourceIsGiven() {
        //Given
        resourceToSave.setTitle("Le dernier métro");
        resourceToSave.setResourceType(ResourceTypeEnum.MOVIE);
        resourceToSave.setImdbId("12345678");
        resourceToSave.setResourceDataSource(CatalogReferenceEnum.OMDBAPI);

        Resource resourceWitness = new Resource();
        resourceWitness.setTitle("Le premier métro");
        resourceWitness.setResourceType(ResourceTypeEnum.MOVIE);
        resourceWitness.setImdbId("87654321");
        resourceWitness.setResourceDataSource(CatalogReferenceEnum.OMDBAPI);

        //When
        Resource result = resourceRepository.save(resourceToSave);

        //Then
        Resource retrieved = resourceRepository.findMovieByImdbId(result.getImdbId());
        Assertions.assertEquals( resourceToSave.getImdbId(), retrieved.getImdbId());
        Assertions.assertNotEquals( resourceWitness.getImdbId(), retrieved.getImdbId());
    }


}
