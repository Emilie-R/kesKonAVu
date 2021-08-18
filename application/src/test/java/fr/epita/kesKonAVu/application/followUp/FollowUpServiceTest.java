package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;
import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUpRepository;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.ResourceRepository;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import fr.epita.kesKonAVu.domain.resource.SerieRepository;
import fr.epita.kesKonAVu.domain.user.Member;
import org.aspectj.weaver.ast.Not;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class FollowUpServiceTest {

    @MockBean
    ResourceFollowUpRepository resourceFollowUpRepository;
    @MockBean
    ResourceRepository resourceRepository;
    @MockBean
    SerieRepository serieRepository;

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
        ResourceFollowUp followUpToCreate = new ResourceFollowUp();
        followUpToCreate.setMember(member);
        followUpToCreate.setResource(resourceToCreate);

        Mockito.when(resourceRepository.findMovieByExternalKey("123456")).thenThrow(NotFoundException.class);
        Mockito.when(resourceRepository.getMovieFromCatalogueByImdbId("123456")).thenReturn(resourceToCreate);
        Mockito.when(resourceRepository.save(Mockito.any(Resource.class))).thenReturn(resourceToCreate);
        Mockito.when(resourceFollowUpRepository.save(Mockito.any(ResourceFollowUp.class))).thenReturn(followUpToCreate);

        //When
        followUpService.createNewFollowUp(followUpToCreate);

        //Then
        Mockito.verify(resourceRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(resourceRepository, Mockito.times(1)).findMovieByExternalKey("123456");
        Mockito.verify(resourceRepository, Mockito.times(1)).getMovieFromCatalogueByImdbId("123456");
        Mockito.verify(resourceFollowUpRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void createNewFollowUp_with_existing_Movie_should_success() {

    }

    @Test
    public void createNewFollowUp_with_new_Serie_should_success() {

    }
}
