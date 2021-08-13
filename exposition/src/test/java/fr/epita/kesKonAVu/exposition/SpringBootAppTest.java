package fr.epita.kesKonAVu.exposition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "fr.epita.kesKonAVu.exposition" }, lazyInit = true)
public class SpringBootAppTest {
    /**
     * @param args
     */
    public static void main(final String[] args) {
        SpringApplication.run(SpringBootAppTest.class, args);
    }
}
