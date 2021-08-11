package fr.epita.kesKonAVu.infrastructure.user;


import fr.epita.kesKonAVu.domain.user.Member;
import fr.epita.kesKonAVu.domain.user.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Override
    public Optional<Member> findById(Long id) {
        return memberJpaRepository.findById(id);
    }

    @Override
    public Member findByPseudo(String pseudo) {
        return memberJpaRepository.findByPseudo(pseudo);
    }

    @Override
    public Member save(Member member) {
        return memberJpaRepository.save(member);
    }
}
