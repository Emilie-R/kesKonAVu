package fr.epita.kesKonAVu.infrastructure.resource;
import fr.epita.kesKonAVu.domain.resource.ExternalKey;
import fr.epita.kesKonAVu.domain.resource.Resource;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ResourceJpaRepository extends JpaRepository<Resource, Long>{

    Resource findByTitleAndResourceType(String title, ResourceTypeEnum resourceTypeEnum);

    Resource findByIdResourceAndResourceType(Long idResource,ResourceTypeEnum resourceTypeEnum);

    Resource findByExternalKeyAndResourceType(String externalKey,ResourceTypeEnum resourceTypeEnum);

    Resource findByExternalKey(ExternalKey externalKey);
}
