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



}
