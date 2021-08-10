import Entities.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DaoResource extends JpaRepository<Resource, Integer> {

}
