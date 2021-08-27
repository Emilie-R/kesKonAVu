package fr.epita.kesKonAVu.application.security;

import fr.epita.kesKonAVu.domain.user.Member;
import fr.epita.kesKonAVu.domain.user.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    // Indique comment rechercher les utilisateurs de l'application
    // transforme des objets du domaine en objet reconnu par SpringSecurity
    @Override
    public UserDetails loadUserByUsername(String idMember) throws UsernameNotFoundException {

        if (!memberRepository.findById(idMember).isPresent()) {
            throw new UsernameNotFoundException(" Id Member " + idMember + " not found");
        }
        final Member member = memberRepository.findById(idMember).get();

        return new User(member.getIdMember(), member.getPassword(), getAuthorities(member));
    }

    //Recherche des Authorities/ Roles du member
    private static Collection<? extends GrantedAuthority> getAuthorities(final Member member){
        final String[] userRoles = member.getRoles().stream().map((role) -> role.name()).toArray(String[]::new);
        final Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
}
