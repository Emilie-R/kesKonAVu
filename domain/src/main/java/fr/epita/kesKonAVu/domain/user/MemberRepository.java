package fr.epita.kesKonAVu.domain.user;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Optional<Member> findById (String id);

    Member findByPseudo (String pseudo);

    Member save (Member member);

    Member findByIdWithAllResourceFollowUps(String id);

}
