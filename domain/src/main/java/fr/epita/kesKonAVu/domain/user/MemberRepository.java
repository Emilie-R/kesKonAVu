package fr.epita.kesKonAVu.domain.user;

import java.util.Optional;

public interface MemberRepository {

    Optional<Member> findById (Long id);

    Member findByPseudo (String pseudo);

    Member save (Member member);

}
