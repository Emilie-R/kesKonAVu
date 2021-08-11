package fr.epita.kesKonAVu.infrastructure.resource;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieJpaRepository extends JpaRepository<Serie,Long> {
    Serie findByTitle(String title);

    Serie findByIdResource(String idResource);

    Serie findByExternalKey (String externalKey);
}
