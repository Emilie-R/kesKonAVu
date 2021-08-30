package fr.epita.kesKonAVu.application.security;

import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.user.Member;
import fr.epita.kesKonAVu.domain.user.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public Member findMemberByPseudo(String pseudo) {
        if (memberRepository.findByPseudo(pseudo) == null) {
            throw new NotFoundException("Member non trouvé dans la base : " + pseudo) ;
        }
        return memberRepository.findByPseudo(pseudo);
    }
}
