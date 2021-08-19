package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.application.SpringBootAppTest;
import fr.epita.kesKonAVu.domain.followUp.FollowUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = { SpringBootAppTest.class })
public class ResourceFollowUpServiceTest {

    @Autowired
    ResourceFollowUpService resourceFollowUpService;

    @MockBean
    FollowUpRepository followUpRepository;



}
