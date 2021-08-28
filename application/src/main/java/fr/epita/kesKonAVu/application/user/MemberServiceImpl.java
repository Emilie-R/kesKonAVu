package fr.epita.kesKonAVu.application.user;

import fr.epita.kesKonAVu.domain.common.AlreadyExistingException;
import fr.epita.kesKonAVu.domain.common.DataFormatException;
import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.user.Member;
import fr.epita.kesKonAVu.domain.user.MemberDomainService;
import fr.epita.kesKonAVu.domain.user.MemberRepository;
import fr.epita.kesKonAVu.domain.user.TypeRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberDomainService memberDomainService;

    @Override
    @Transactional
    public Member createMember(Member member) {

        // Check member pseudo
        if (!memberDomainService.checkMemberPseudo(member.getPseudo())) {
            throw new DataFormatException("Format du pseudo incorrect");
        }
        // Check member password
        if (!memberDomainService.checkMemberPassword(member.getPassword()))
        {
            throw new DataFormatException("Format du password incorrect");
        }

        // Check if pseudo isn't already used
        if (memberRepository.findByPseudo(member.getPseudo()) != null) {
            throw new AlreadyExistingException("pseudo déjà enrôlé dans l'application");
        }

        // Set member Role
        final Set<TypeRoleEnum> roleUser = new HashSet<>();
        roleUser.add(TypeRoleEnum.USER);
        member.setRoles(roleUser);

        //Set Creation Date
        member.setCreationDate(LocalDate.now());

        // Encode memberPassword
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        // Return created member in DataBase
        return memberRepository.save(member);
    }


    @Override
    @Transactional(readOnly = true)
    public Member findOne(String pseudo) {
        if (memberRepository.findByPseudo(pseudo) == null) {
            throw new NotFoundException("Member non trouvé dans la base : " + pseudo) ;
        }
        return memberRepository.findByPseudo(pseudo);
    }

    @Override
    @Transactional(readOnly = true)
    public Member findByIdWithAllResourceFollowUps (String id) {
        if (memberRepository.findByIdWithAllResourceFollowUps(id) == null ) {
          throw new NotFoundException("Member with FollowUps not found - Id Member : " + id );
        }
        return memberRepository.findByIdWithAllResourceFollowUps(id);
    }


    @Override
    public void updateMember(Member member) {
        // TODO - Don't needed actually
    }

}
