package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.catalogue.CatalogueService;
import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeStatusEnum;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.followUp.FollowUpRepository;
import fr.epita.kesKonAVu.domain.resource.*;
import fr.epita.kesKonAVu.domain.user.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class FollowUpServiceTest {

    @Autowired
    FollowUpRepository followUpRepository;
    @MockBean
    ResourceRepository resourceRepository;
    @MockBean
    SerieRepository serieRepository;
    @MockBean
    CatalogueService catalogueService;

    @Autowired
    FollowUpService followUpService;

    @Test
    public void createNewFollowUp_with_new_Movie_should_success() {
        //Given
        Member member = new Member();
        member.setIdMember(12L);
        Resource resourceToCreate = new Resource();
        resourceToCreate.setExternalKey("123456");
        resourceToCreate.setResourceType(ResourceTypeEnum.MOVIE);
        FollowUp followUpToCreate = new FollowUp();
        followUpToCreate.setMember(member);
        followUpToCreate.setResource(resourceToCreate);

        Mockito.when(resourceRepository.findMovieByExternalKey("123456")).thenThrow(NotFoundException.class);
        Mockito.when(catalogueService.findMovieByImdbId("123456")).thenReturn(resourceToCreate);
        Mockito.when(resourceRepository.save(Mockito.any(Resource.class))).thenReturn(resourceToCreate);
        Mockito.when(followUpRepository.save(Mockito.any(FollowUp.class))).thenReturn(followUpToCreate);

        //When
        FollowUp followUpCreated = followUpService.createNewFollowUp(followUpToCreate);

        //Then
        Assertions.assertNotNull(followUpCreated);
        Mockito.verify(resourceRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(resourceRepository, Mockito.times(1)).findMovieByExternalKey("123456");
        Mockito.verify(catalogueService, Mockito.times(1)).findMovieByImdbId("123456");
        Mockito.verify(followUpRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void createNewFollowUp_with_existing_Movie_should_success() {
        Member member = new Member();
        member.setIdMember(12L);
        Resource resource = new Resource();
        resource.setIdResource(1L);
        resource.setExternalKey("123456");
        resource.setResourceType(ResourceTypeEnum.MOVIE);
        FollowUp followUpToCreate = new FollowUp();
        followUpToCreate.setMember(member);
        followUpToCreate.setResource(resource);

        Mockito.when(resourceRepository.findMovieByExternalKey("123456")).thenReturn(resource);
        Mockito.when(followUpRepository.findByResourceAndMember(resource,member)).thenReturn(null);
        Mockito.when(followUpRepository.save(Mockito.any(FollowUp.class))).thenReturn(followUpToCreate);

        //When
        FollowUp followUpCreated = followUpService.createNewFollowUp(followUpToCreate);

        //Then
        Assertions.assertNotNull(followUpCreated);
        Mockito.verify(resourceRepository, Mockito.times(1)).findMovieByExternalKey("123456");
        Mockito.verify(followUpRepository, Mockito.times(1)).findByResourceAndMember(resource, member);
        Mockito.verify(followUpRepository, Mockito.times(1)).save(Mockito.any(FollowUp.class));
    }

    @Test
    public void createNewFollowUp_with_new_Serie_should_success() {
        Member member = new Member();
        member.setIdMember(12L);
        Serie resourceToCreate = new Serie();
        resourceToCreate.setExternalKey("123456");
        resourceToCreate.setResourceType(ResourceTypeEnum.SERIE);
        resourceToCreate.setNumberOfSeasons(1);

        Episode episode1 = new Episode();
        episode1.setNumber(1);
        episode1.setExternalKey("56789");
        episode1.setExternalCatalogName(CatalogReferenceEnum.OMDBAPI);
        episode1.setSeasonNumber(1);
        Episode episode2 = new Episode();
        episode2.setNumber(2);
        episode2.setExternalKey("56789");
        episode2.setExternalCatalogName(CatalogReferenceEnum.OMDBAPI);
        episode2.setSeasonNumber(1);
        Set<Episode> episodes = new HashSet<>();
        episodes.add(episode1);
        episodes.add(episode2);
        resourceToCreate.setEpisodes(episodes);

        FollowUp followUpToCreate = new FollowUp();
        followUpToCreate.setMember(member);
        followUpToCreate.setResource(resourceToCreate);

        Mockito.when(serieRepository.findByExternalKey("123456")).thenThrow(NotFoundException.class);
        Mockito.when(catalogueService.findSerieByImdbId("123456")).thenReturn(resourceToCreate);
        Mockito.when(catalogueService.findAllEpisodes(Mockito.any(Serie.class))).thenReturn(episodes);
        Mockito.when(resourceRepository.save(Mockito.any(Serie.class))).thenReturn(resourceToCreate);
        Mockito.when(followUpRepository.save(Mockito.any(FollowUp.class))).thenReturn(followUpToCreate);

        //When
        FollowUp followUpCreated = followUpService.createNewFollowUp(followUpToCreate);

        //Then
        Assertions.assertNotNull(followUpCreated);
        Mockito.verify(serieRepository, Mockito.times(1)).save(Mockito.any(Serie.class));
        Mockito.verify(catalogueService, Mockito.times(1)).findAllEpisodes(Mockito.any(Serie.class));
        Mockito.verify(serieRepository, Mockito.times(1)).findByExternalKey("123456");
        Mockito.verify(catalogueService, Mockito.times(1)).findSerieByImdbId("123456");
        Mockito.verify(followUpRepository, Mockito.times(1)).save(Mockito.any(FollowUp.class));
    }

    @Test
    public void  SaveSerieProgression(){
        FollowUp f1 = followUpRepository.findByIdWithAllEpisodeFollowUps(3L);

        f1.getEpisodeFollowUps().stream()
                        .forEach(e-> e.setStatus(EpisodeStatusEnum.VU));

        Long id = followUpService.SaveSerieProgression(f1);

        FollowUp f2 = followUpRepository.findById(id).get();

        Long filteredCount = f2.getEpisodeFollowUps().stream()
                        .filter(e-> e.getStatus() == EpisodeStatusEnum.VU).count();

        Assertions.assertEquals(5,filteredCount);
        Assertions.assertEquals(100,f2.getProgression());

    }
}
