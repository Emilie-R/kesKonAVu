package fr.epita.kesKonAVu.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@ComponentScan(basePackages = {"fr.epita.kesKonAVu"}, lazyInit = true)
@EntityScan(basePackages = {"fr.epita.kesKonAVu.domain"})
@EnableJpaRepositories
public class AppTest {

    public static void main(String[] args) {
        SpringApplication.run(AppTest.class,args);
    }
}
