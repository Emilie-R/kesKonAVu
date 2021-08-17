package fr.epita.kesKonAVu.infrastructure.resource;

import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import fr.epita.kesKonAVu.domain.resource.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieJpaRepository extends JpaRepository<Serie,Long> {
    Serie findByTitleAndResourceType(String title, ResourceTypeEnum resourceTypeEnum);

    Serie findByIdResourceAndResourceType(Long idResource,ResourceTypeEnum resourceTypeEnum);

    Serie findByExternalKeyAndResourceType(String externalKey,ResourceTypeEnum resourceTypeEnum);
}
