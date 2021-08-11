package fr.epita.kesKonAVu.infrastructure.user;

import fr.epita.kesKonAVu.domain.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    Member findByPseudo(String pseudo);

}
