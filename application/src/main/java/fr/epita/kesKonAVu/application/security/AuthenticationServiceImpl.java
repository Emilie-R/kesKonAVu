package fr.epita.kesKonAVu.application.security;

import fr.epita.kesKonAVu.domain.common.InvalidCredentialsException;
import fr.epita.kesKonAVu.domain.user.Member;
import fr.epita.kesKonAVu.domain.user.MemberDomainService;
import fr.epita.kesKonAVu.domain.user.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberDomainService memberDomainService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public Member authenticateMember(String pseudo, String password) throws InvalidCredentialsException {

        // Si member non trouvé : Exception

        final Member member = memberRepository.findByPseudo(pseudo)
                .orElseThrow(() -> {
                    // Tentative de connexion KO - à logger
                    String pass ="";
                    if (!password.equals("") && password!= null) {
                        pass = "***";
                    }
                    LOG.error("Connexion KO : pseudo/ password = " + pseudo + " / " + pass);

                    return new InvalidCredentialsException("Member absent dans la base : " + pseudo);
                });
        // Vérification de l'id Member avec Mot de passe
        authenticate(member.getIdMember(), password, pseudo);

        // Tentative de connexion réussie - Mise à jour de la date de dernière connexion du member
        final Member memberToSave = memberDomainService.duplicateMember(member);
        memberToSave.setLastConnexionDateTime(LocalDateTime.now());
        memberRepository.save(memberToSave);

        return member;
    }

    private void authenticate(final String idMember, final String password, final String pseudo) throws InvalidCredentialsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(idMember, password));

        } catch (final DisabledException e) {
            // Tentative de connexion KO - à logger
            String pass ="";
            if (!password.equals("") && password!= null) {
                pass = "***";
            }
            LOG.error("Connexion KO : pseudo / password / idMember = " + pseudo + " / " + pass + " / " + idMember);
            throw new InvalidCredentialsException("USER_DISABLED", e);

        } catch (final BadCredentialsException e) {
            //Tentative de connexion KO - à logger
            String pass ="";
            if (!password.equals("") && password!= null) {
                pass = "***";
            }
            LOG.error("Connexion KO : pseudo / password / idMember = " + pseudo + " / " + pass + " / " + idMember);
            throw new InvalidCredentialsException("INVALID_CREDENTIALS", e);
        }
    }
}
