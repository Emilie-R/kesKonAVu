package fr.epita.kesKonAVu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "fr.epita.kesKonAVu" }, lazyInit = true)
@EntityScan(basePackages = {"fr.epita.kesKonAVu.domain"})
@EnableJpaRepositories
public class SpringBootAppTest {
    /**
     * @param args
     */
    public static void main(final String[] args) {
        SpringApplication.run(SpringBootAppTest.class, args);
    }
}
