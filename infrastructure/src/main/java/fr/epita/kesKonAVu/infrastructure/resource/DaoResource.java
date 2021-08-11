package fr.epita.kesKonAVu.infrastructure.resource;

import fr.epita.kesKonAVu.domain.resource.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DaoResource extends JpaRepository<Resource, Long> {

}
