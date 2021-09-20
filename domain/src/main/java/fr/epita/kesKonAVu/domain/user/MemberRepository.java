package fr.epita.kesKonAVu.domain.user;

import java.util.Optional;

public interface MemberRepository {

    Optional<Member> findById (String id);

    Optional<Member> findByPseudo (String pseudo);

    Member save (Member member);

    Member findByIdWithAllResourceFollowUps(String id);

}
