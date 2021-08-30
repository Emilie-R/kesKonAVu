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
        Assertions.assertEquals("tt0095250", result.getExternalKey());
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
        resourceToSave.setExternalKey("12345678");
        resourceToSave.setExternalCatalogName(CatalogReferenceEnum.OMDBAPI);

        //When
        Resource result = resourceRepository.save(resourceToSave);

        //Then
        Assertions.assertNotNull(resourceRepository.findById(result.getIdResource()));
        Assertions.assertEquals( resourceToSave.getTitle(),
                resourceRepository.findById(result.getIdResource()).getTitle());
        Assertions.assertEquals( resourceToSave.getExternalKey(),
                resourceRepository.findById(result.getIdResource()).getExternalKey());
    }

    @Test
    public void given_existing_resource_save_should_success() {
        //Given
        Resource resourceToSave = resourceRepository.findById(1L);
        resourceToSave.setTitle("Tarzan");
        resourceToSave.setExternalKey("12345678");

        //When
        Resource result = resourceRepository.save(resourceToSave);

        //Then
        Assertions.assertEquals(resourceToSave.getTitle(), resourceRepository.findById(1L).getTitle());
        Assertions.assertEquals(resourceToSave.getExternalKey(),
                resourceRepository.findById(1L).getExternalKey());
        Assertions.assertEquals(resourceToSave.getIdResource(), result.getIdResource());
        Assertions.assertEquals(resourceToSave.getExternalKey(), result.getExternalKey());
    }

    @Test
    public void ShouldFindByTitleWhenResourceIsGiven() {
        //Given
        resourceToSave.setTitle("Le dernier métro");
        resourceToSave.setResourceType(ResourceTypeEnum.MOVIE);
        resourceToSave.setExternalKey("12345678");
        resourceToSave.setExternalCatalogName(CatalogReferenceEnum.OMDBAPI);

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
        resourceToSave.setExternalKey("12345678");
        resourceToSave.setExternalCatalogName(CatalogReferenceEnum.OMDBAPI);

        Resource resourceWitness = new Resource();
        resourceWitness.setTitle("Le premier métro");
        resourceWitness.setResourceType(ResourceTypeEnum.MOVIE);
        resourceWitness.setExternalKey("87654321");
        resourceWitness.setExternalCatalogName(CatalogReferenceEnum.OMDBAPI);

        //When
        Resource result = resourceRepository.save(resourceToSave);

        //Then
        Resource retrieved = resourceRepository.findMovieByExternalKey(result.getExternalKey());
        Assertions.assertEquals( resourceToSave.getExternalKey(), retrieved.getExternalKey());
        Assertions.assertNotEquals( resourceWitness.getExternalKey(), retrieved.getExternalKey());
    }
    @Test
    public void FindResourceFailedShouldThrowNotFoundException() {
        //Given
        resourceToSave.setTitle("Le dernier métro");
        resourceToSave.setResourceType(ResourceTypeEnum.MOVIE);
        resourceToSave.setExternalKey("12345678");
        resourceToSave.setExternalCatalogName(CatalogReferenceEnum.OMDBAPI);

        Resource resourceWitness = new Resource();
        resourceWitness.setTitle("Le premier métro");
        resourceWitness.setResourceType(ResourceTypeEnum.MOVIE);
        resourceWitness.setExternalKey("87654321");
        resourceWitness.setExternalCatalogName(CatalogReferenceEnum.OMDBAPI);

        //When
        Resource result = resourceRepository.save(resourceToSave);

        //Then
        Assertions.assertThrows(NotFoundException.class,()-> resourceRepository.findMovieByExternalKey("8765432"));
        Assertions.assertThrows(NotFoundException.class,()-> resourceRepository.findMovieByTitle("Le second métro"));
        Assertions.assertThrows(NotFoundException.class,()-> resourceRepository.findById(40L));
    }


}
