package fr.epita.kesKonAVu.application.user;

import fr.epita.kesKonAVu.domain.common.AlreadyExistingException;
import fr.epita.kesKonAVu.domain.common.DataFormatException;
import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.user.Member;
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

    private final int miniPseudoSize = 6;
    private final int miniPasswordSize = 6;


    @Override
    public Member createMember(Member member) {

        // Check member pseudo
        if (!this.checkMemberDataFormat(member.getPseudo(), miniPseudoSize)) {
            throw new DataFormatException("pseudo inférieur à la longueur minimale - 6 caractères");
        }

        if (memberRepository.findByPseudo(member.getPseudo()) != null) {
            throw new AlreadyExistingException("pseudo déjà enrôlé dans l'application");
        }

        // Check member password
        if (!this.checkMemberDataFormat(member.getPassword(), miniPasswordSize))
        {
            throw new DataFormatException("password inférieur à la longueur minimale - 6 caractères");
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
    public void updateMember(Member member) {
        // TODO - Don't needed actually
    }

    @Override
    @Transactional(readOnly = true)
    public Member findOne(Long id) {
        if (memberRepository.findById(id).isPresent())
        {
            return memberRepository.findById(id).get();
        } else {
            throw new NotFoundException("member non trouvé en BDD");
        }
    }

    @Override
    public Member findByIdWithAllResourceFollowUps (Long id) {
        return memberRepository.findByIdWithAllResourceFollowUps(id);
    }

    /**
     * In order to control the format of a member data
     * @param dataToControl data to check
     * @param expectedMinimumSize expected minimum size
     * @return the data is correct or not
     */
    private Boolean checkMemberDataFormat (String dataToControl, int expectedMinimumSize) {
        return dataToControl.replaceAll("//s", "").length() >= expectedMinimumSize;
    }
}
