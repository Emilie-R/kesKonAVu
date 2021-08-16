package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.application.SpringBootAppTest;
import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;
import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUpRepository;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import fr.epita.kesKonAVu.domain.resource.Serie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = { SpringBootAppTest.class })
public class ResourceFollowUpServiceTest {

    @Autowired
    ResourceFollowUpService resourceFollowUpService;

    @MockBean
    ResourceFollowUpRepository resourceFollowUpRepository;

    @Test
    public void SeparateByStatusWhenResourceFollowUpsIsGiven(){
        // les inputs
        //1
        ResourceFollowUp resourceFollowUp1 = new ResourceFollowUp();
        resourceFollowUp1.setStatus(StatusEnum.VU);
        resourceFollowUp1.setIdFollowUp(1L);
        Resource resource1 = new Resource();
        resource1.setIdResource(1L);
        resource1.setTitle("Godzilla");
        resource1.setResourceType(ResourceTypeEnum.MOVIE);
        resourceFollowUp1.setResource(resource1);
        Resource resource2 = new Resource();
        //2
        resource2.setIdResource(resource1.getIdResource()+1);
        resource2.setTitle("GodZilla Origin");
        resource2.setResourceType(ResourceTypeEnum.MOVIE);
        ResourceFollowUp resourceFollowUp2 = new ResourceFollowUp();
        resourceFollowUp2.setStatus(StatusEnum.AVOIR);
        resourceFollowUp2.setIdFollowUp(resourceFollowUp1.getIdFollowUp()+1);
        resourceFollowUp2.setResource(resource2);
        //3
        Serie resource3 = new Serie();
        resource3.setIdResource(resource2.getIdResource()+1);
        resource3.setTitle("Friends");
        resource3.setResourceType(ResourceTypeEnum.SERIE);
        ResourceFollowUp resourceFollowUp3 = new ResourceFollowUp();
        resourceFollowUp3.setStatus(StatusEnum.VU);
        resourceFollowUp3.setIdFollowUp(resourceFollowUp1.getIdFollowUp()+1);
        resourceFollowUp3.setResource(resource3);
        // liste de followUps
        List<ResourceFollowUp> liste = new ArrayList<>();
        liste.add(resourceFollowUp1);
        liste.add(resourceFollowUp2);
        liste.add(resourceFollowUp3);
        //test
        Map<StatusEnum,List<ResourceFollowUp>> map = resourceFollowUpService.SeparateByStatus(liste);
        Assertions.assertEquals(2,map.get(StatusEnum.VU).size()); // 2 car mock du ResourceFollowUpRepository
        Assertions.assertEquals(1,map.get(StatusEnum.AVOIR).size());


    }
    @Test
    public void SortByRatingWhenResourceFollowUpsIsGiven(){
        // les inputs
        //1
        ResourceFollowUp resourceFollowUp1 = new ResourceFollowUp();
        resourceFollowUp1.setStatus(StatusEnum.VU);
        resourceFollowUp1.setNote(10);
        Resource resource1 = new Resource();
        resource1.setIdResource(1L);
        resource1.setTitle("Godzilla");
        resource1.setResourceType(ResourceTypeEnum.MOVIE);
        resourceFollowUp1.setResource(resource1);
        //2
        Resource resource2 = new Resource();
        resource2.setIdResource(resource1.getIdResource()+1);
        resource2.setTitle("GodZilla Origin");
        resource2.setResourceType(ResourceTypeEnum.MOVIE);
        ResourceFollowUp resourceFollowUp2 = new ResourceFollowUp();
        resourceFollowUp2.setStatus(StatusEnum.AVOIR);
        resourceFollowUp2.setNote(20);
        resourceFollowUp2.setResource(resource2);
        //3
        Serie resource3 = new Serie();
        resource3.setIdResource(resource2.getIdResource()+1);
        resource3.setTitle("Friends");
        resource3.setResourceType(ResourceTypeEnum.SERIE);
        ResourceFollowUp resourceFollowUp3 = new ResourceFollowUp();
        resourceFollowUp3.setStatus(StatusEnum.VU);
        resourceFollowUp3.setNote(5);
        resourceFollowUp3.setResource(resource3);
        // liste de followUps
        List<ResourceFollowUp> liste = new ArrayList<>();
        liste.add(resourceFollowUp1);
        liste.add(resourceFollowUp2);
        liste.add(resourceFollowUp3);
        //test
        List<Resource> resources = resourceFollowUpService.SortByCriteria(liste,SortCriteriaEnum.RATING);
        Assertions.assertEquals(resource3.getTitle(),resources.get(2).getTitle()); // Friends à la + faible note
        Assertions.assertEquals(resource2.getTitle(),resources.get(0).getTitle());//1er = Godzilla Origin


    }
    @Test
    public void SortByDateWhenResourceFollowUpsIsGiven(){
        // les inputs
        //1
        ResourceFollowUp resourceFollowUp1 = new ResourceFollowUp();
        resourceFollowUp1.setStatus(StatusEnum.VU);
        resourceFollowUp1.setNote(10);
        Resource resource1 = new Resource();
        resource1.setIdResource(1L);
        resource1.setTitle("Godzilla");
        resource1.setResourceType(ResourceTypeEnum.MOVIE);
        resourceFollowUp1.setResource(resource1);
        resourceFollowUp1.setLastModificationDate(LocalDate.now());
        //2
        Resource resource2 = new Resource();
        resource2.setIdResource(resource1.getIdResource()+1);
        resource2.setTitle("GodZilla Origin");
        resource2.setResourceType(ResourceTypeEnum.MOVIE);
        ResourceFollowUp resourceFollowUp2 = new ResourceFollowUp();
        resourceFollowUp2.setStatus(StatusEnum.AVOIR);
        resourceFollowUp2.setNote(20);
        resourceFollowUp2.setResource(resource2);
        resourceFollowUp2.setLastModificationDate(resourceFollowUp1.getLastModificationDate().plusDays(1));
        //3
        Serie resource3 = new Serie();
        resource3.setIdResource(resource2.getIdResource()+1);
        resource3.setTitle("Friends");
        resource3.setResourceType(ResourceTypeEnum.SERIE);
        ResourceFollowUp resourceFollowUp3 = new ResourceFollowUp();
        resourceFollowUp3.setStatus(StatusEnum.VU);
        resourceFollowUp3.setNote(5);
        resourceFollowUp3.setResource(resource3);
        resourceFollowUp3.setLastModificationDate(resourceFollowUp2.getLastModificationDate().plusDays(1));
        // liste de followUps
        List<ResourceFollowUp> liste = new ArrayList<>();
        liste.add(resourceFollowUp1);
        liste.add(resourceFollowUp2);
        liste.add(resourceFollowUp3);
        //test
        List<Resource> resources = resourceFollowUpService.SortByCriteria(liste,SortCriteriaEnum.DATE);
        Assertions.assertEquals(resource1.getTitle(),resources.get(2).getTitle()); // Godzilla en dernier
        Assertions.assertEquals(resource3.getTitle(),resources.get(0).getTitle());//1er = Friends


    }

}
