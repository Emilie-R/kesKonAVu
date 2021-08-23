package fr.epita.kesKonAVu.infrastructure.followUp;

import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.followUp.FollowUpRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class FollowUpRepositoryTest {

    @Autowired
    FollowUpRepository followUpRepository;


    @Test
    public void  getFollowUpWithitsEpisodeFollowUpsOK(){
        FollowUp f1 = followUpRepository.findByIdWithAllEpisodeFollowUps(3L);

        Assertions.assertEquals(5,f1.getEpisodeFollowUps().size());

    }
}
