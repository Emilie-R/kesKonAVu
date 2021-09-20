package fr.epita.kesKonAVu.application.user;

import fr.epita.kesKonAVu.domain.common.AlreadyExistingException;
import fr.epita.kesKonAVu.domain.common.DataFormatException;
import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import fr.epita.kesKonAVu.domain.user.Member;
import fr.epita.kesKonAVu.domain.user.MemberDomainService;
import fr.epita.kesKonAVu.domain.user.MemberRepository;
import fr.epita.kesKonAVu.domain.user.TypeRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.management.MemoryManagerMXBean;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        {   throw new DataFormatException("Format du password incorrect");
        }

        // Check if pseudo isn't already used for an other member
        if (memberRepository.findByPseudo(member.getPseudo()).isPresent()) {
            throw new AlreadyExistingException("Member déjà enrôlé dans l'application");
        }

        // Set member Role
        final Set<TypeRoleEnum> roleUser = new HashSet<>();
        roleUser.add(TypeRoleEnum.USER);
        member.setRoles(roleUser);

        //Set Creation Date
        member.setCreationDate(LocalDate.now());

        // Encode memberPassword
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        //Created member from DataBase
        final Member memberCreated = memberRepository.save(member);

        // La tentative de connexion est considérée comme réussie - enregistrement de la date de dernière connexion
        final Member memberToUpdate = memberDomainService.duplicateMember(memberCreated);
        memberToUpdate.setLastConnexionDateTime(LocalDateTime.now());
        memberRepository.save(memberToUpdate);

        return memberCreated;
    }


    @Override
    @Transactional(readOnly = true)
    @Secured("ADMIN")
    public Member findOne(String pseudo) {
        return memberRepository.findByPseudo(pseudo)
                .orElseThrow( () -> new NotFoundException("Member non trouvé dans la base : " + pseudo));
    }

    @Override
    @Transactional(readOnly = true)
    public Member findByIdWithAllResourceFollowUps (String id, Optional<ResourceTypeEnum> typeResource) {
        if (memberRepository.findByIdWithAllResourceFollowUps(id) == null )  {
          throw new NotFoundException("Member with FollowUps not found - Id Member : " + id );
        }

        Member memberWithFollowUps = memberRepository.findByIdWithAllResourceFollowUps(id);

        if (typeResource.isPresent()) {
            Set<FollowUp> newList =
            memberRepository.findByIdWithAllResourceFollowUps(id).getFollowUps().stream()
                    .filter((FollowUp followUp) -> followUp.getResource().getResourceType().equals(typeResource.get()))
                    .collect(Collectors.toSet());
            memberWithFollowUps.setFollowUps(newList);
        }

        return memberWithFollowUps;
    }


    @Override
    public void updateMember(Member member) {
        // TODO - Don't needed actually
    }

}
