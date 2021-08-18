package fr.epita.kesKonAVu;

import fr.epita.kesKonAVu.domain.catalogue.CatalogueService;
import fr.epita.kesKonAVu.domain.catalogue.CatalogueServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootConfiguration
@ComponentScan(basePackages = {"fr.epita.kesKonAVu"})
@EntityScan(basePackages = {"fr.epita.kesKonAVu.domain"})
@EnableJpaRepositories
public class AppTest {

    public static void main(String[] args) {
        SpringApplication.run(AppTest.class,args);
    }

    @Bean
    CatalogueService catalogueService() {
        return new CatalogueServiceImpl();
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
