package fr.epita.kesKonAVu.infrastructure.user;

import fr.epita.kesKonAVu.domain.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    Member findByPseudo(String pseudo);

    @Query("select m from Member m left join fetch m.followUps where m.id = :id")
    Member findByIdWithAllResourceFollowUps(@Param("id") Long id);

}
