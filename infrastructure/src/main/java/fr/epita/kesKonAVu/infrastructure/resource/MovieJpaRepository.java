package fr.epita.kesKonAVu.infrastructure.resource;

import fr.epita.kesKonAVu.domain.resource.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieJpaRepository extends JpaRepository<Movie, Long> {

    Movie findByTitle(String title);

    Movie findByImdbId(String externalKey);
}
