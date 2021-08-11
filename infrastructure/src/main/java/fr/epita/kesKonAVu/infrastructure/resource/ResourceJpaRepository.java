package fr.epita.kesKonAVu.infrastructure.resource;
import fr.epita.kesKonAVu.domain.resource.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ResourceJpaRepository extends JpaRepository<Resource, Long>{

    Resource findByTitle(String title);

    Resource findByIdResource(String idResource);

    Resource findByExternalKey(String externalKey);
}
