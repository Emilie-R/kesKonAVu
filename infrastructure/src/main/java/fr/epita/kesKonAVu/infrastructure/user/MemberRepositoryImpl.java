package fr.epita.kesKonAVu.infrastructure.user;

import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import fr.epita.kesKonAVu.domain.user.Member;
import fr.epita.kesKonAVu.domain.user.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberRepositoryImpl implements MemberRepository {

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Override
    public Optional<Member> findById(String id) {
        return memberJpaRepository.findById(id);
    }

    @Override
    public Optional<Member> findByPseudo(String pseudo) {
        return Optional.ofNullable(memberJpaRepository.findByPseudo(pseudo));
    }

    @Override
    public Member save(Member member) {
        return memberJpaRepository.save(member);
    }

    @Override
    public Member findByIdWithAllResourceFollowUps (String id) {
        return memberJpaRepository.findByIdWithAllResourceFollowUps(id);
    }
}
