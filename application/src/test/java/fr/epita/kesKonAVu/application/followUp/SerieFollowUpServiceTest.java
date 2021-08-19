package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.application.SpringBootAppTest;
import fr.epita.kesKonAVu.domain.followUp.SerieFollowUp;
import fr.epita.kesKonAVu.domain.followUp.SerieFollowUpRepository;
import fr.epita.kesKonAVu.domain.followUp.statusEnum;
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
public class SerieFollowUpServiceTest {

    @Autowired
    SerieFollowUpService serieFollowUpService;

    @MockBean
    SerieFollowUpRepository serieFollowUpRepository;

    @Test
    public void SeparateByStatusWhenResourceFollowUpsIsGiven(){
        // les inputs
        //1
        SerieFollowUp resourceFollowUp1 = new SerieFollowUp();
        resourceFollowUp1.setStatus(statusEnum.VU);
        resourceFollowUp1.setIdFollowUp(1L);
        Serie resource1 = new Serie();
        resource1.setIdResource(1L);
        resource1.setTitle("Godzilla");
        resource1.setResourceType(ResourceTypeEnum.MOVIE);
        resourceFollowUp1.setResource(resource1);
        //2
        Serie resource2 = new Serie();
        resource2.setIdResource(resource1.getIdResource()+1);
        resource2.setTitle("GodZilla Origin");
        resource2.setResourceType(ResourceTypeEnum.MOVIE);
        SerieFollowUp resourceFollowUp2 = new SerieFollowUp();
        resourceFollowUp2.setStatus(statusEnum.AVOIR);
        resourceFollowUp2.setIdFollowUp(resourceFollowUp1.getIdFollowUp()+1);
        resourceFollowUp2.setResource(resource2);
        //3
        Serie resource3 = new Serie();
        resource3.setIdResource(resource2.getIdResource()+1);
        resource3.setTitle("Friends");
        resource3.setResourceType(ResourceTypeEnum.SERIE);
        SerieFollowUp resourceFollowUp3 = new SerieFollowUp();
        resourceFollowUp3.setStatus(statusEnum.VU);
        resourceFollowUp3.setIdFollowUp(resourceFollowUp1.getIdFollowUp()+1);
        resourceFollowUp3.setResource(resource3);
        // liste de followUps
        List<SerieFollowUp> liste = new ArrayList<>();
        liste.add(resourceFollowUp1);
        liste.add(resourceFollowUp2);
        liste.add(resourceFollowUp3);
        //test
        Map<statusEnum, List<SerieFollowUp>> map = serieFollowUpService.SeparateByStatus(liste);
        Assertions.assertEquals(2,map.get(statusEnum.VU).size()); // 2 car mock du ResourceFollowUpRepository
        Assertions.assertEquals(1,map.get(statusEnum.AVOIR).size());


    }
    @Test
    public void SortByRatingWhenResourceFollowUpsIsGiven(){
        // les inputs
        //1
        SerieFollowUp resourceFollowUp1 = new SerieFollowUp();
        resourceFollowUp1.setStatus(statusEnum.VU);
        resourceFollowUp1.setNote(10);
        Serie resource1 = new Serie();
        resource1.setIdResource(1L);
        resource1.setTitle("Godzilla");
        resource1.setResourceType(ResourceTypeEnum.MOVIE);
        resourceFollowUp1.setResource(resource1);
        //2
        Serie resource2 = new Serie();
        resource2.setIdResource(resource1.getIdResource()+1);
        resource2.setTitle("GodZilla Origin");
        resource2.setResourceType(ResourceTypeEnum.MOVIE);
        SerieFollowUp resourceFollowUp2 = new SerieFollowUp();
        resourceFollowUp2.setStatus(statusEnum.AVOIR);
        resourceFollowUp2.setNote(20);
        resourceFollowUp2.setResource(resource2);
        //3
        Serie resource3 = new Serie();
        resource3.setIdResource(resource2.getIdResource()+1);
        resource3.setTitle("Friends");
        resource3.setResourceType(ResourceTypeEnum.SERIE);
        SerieFollowUp resourceFollowUp3 = new SerieFollowUp();
        resourceFollowUp3.setStatus(statusEnum.VU);
        resourceFollowUp3.setNote(5);
        resourceFollowUp3.setResource(resource3);
        // liste de followUps
        List<SerieFollowUp> liste = new ArrayList<>();
        liste.add(resourceFollowUp1);
        liste.add(resourceFollowUp2);
        liste.add(resourceFollowUp3);
        //test
        List<Serie> resources = serieFollowUpService.SortByCriteria(liste,SortCriteriaEnum.RATING);
        Assertions.assertEquals(resource3.getTitle(),resources.get(2).getTitle()); // Friends à la + faible note
        Assertions.assertEquals(resource2.getTitle(),resources.get(0).getTitle());//1er = Godzilla Origin


    }
    @Test
    public void SortByDateOrByStatusWhenResourceFollowUpsIsGiven(){
        // les inputs
        //1
        SerieFollowUp resourceFollowUp1 = new SerieFollowUp();
        resourceFollowUp1.setStatus(statusEnum.VU);
        resourceFollowUp1.setNote(10);
        Serie resource1 = new Serie();
        resource1.setIdResource(1L);
        resource1.setTitle("Godzilla");
        resource1.setResourceType(ResourceTypeEnum.MOVIE);
        resourceFollowUp1.setResource(resource1);
        resourceFollowUp1.setLastModificationDate(LocalDate.now());
        //2
        Serie resource2 = new Serie();
        resource2.setIdResource(resource1.getIdResource()+1);
        resource2.setTitle("GodZilla Ultimate");
        resource2.setResourceType(ResourceTypeEnum.SERIE);
        SerieFollowUp resourceFollowUp2 = new SerieFollowUp();
        resourceFollowUp2.setStatus(statusEnum.AVOIR);
        resourceFollowUp2.setNote(20);
        resourceFollowUp2.setResource(resource2);
        resourceFollowUp2.setLastModificationDate(resourceFollowUp1.getLastModificationDate().plusDays(1));
        //3
        Serie resource3 = new Serie();
        resource3.setIdResource(resource2.getIdResource()+1);
        resource3.setTitle("Friends");
        resource3.setResourceType(ResourceTypeEnum.SERIE);
        SerieFollowUp resourceFollowUp3 = new SerieFollowUp();
        resourceFollowUp3.setStatus(statusEnum.VU);
        resourceFollowUp3.setNote(5);
        resourceFollowUp3.setResource(resource3);
        resourceFollowUp3.setLastModificationDate(resourceFollowUp2.getLastModificationDate().plusDays(1));
        // liste de followUps
        List<SerieFollowUp> liste = new ArrayList<>();
        liste.add(resourceFollowUp1);
        liste.add(resourceFollowUp2);
        liste.add(resourceFollowUp3);
        //test
        List<Serie> resources = serieFollowUpService.SortByCriteria(liste,SortCriteriaEnum.DATE);
        Assertions.assertEquals(resource1.getTitle(),resources.get(2).getTitle()); // Godzilla en dernier
        Assertions.assertEquals(resource3.getTitle(),resources.get(0).getTitle());//1er = Friends
        // tri par status
        List<Serie> resources2 = serieFollowUpService.SortByCriteria(liste,SortCriteriaEnum.STATUS);
        Assertions.assertEquals(resource2.getTitle(),resources2.get(0).getTitle()); // Godzilla Ultimate en dernier


    }

}
