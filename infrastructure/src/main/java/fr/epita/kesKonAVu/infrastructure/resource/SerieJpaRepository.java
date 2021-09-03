package fr.epita.kesKonAVu.infrastructure.resource;

import fr.epita.kesKonAVu.domain.resource.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieJpaRepository extends JpaRepository<Serie,Long> {
    Serie findByTitle(String title);

    Serie findByIdResource(Long idResource);

    Serie findByImdbId(String externalKey);

    @Query("select s from Serie s left join fetch s.episodes where s.id = :id")
    Serie findByIdWithAllEpisodes (@Param("id") Long idSerie);
}
