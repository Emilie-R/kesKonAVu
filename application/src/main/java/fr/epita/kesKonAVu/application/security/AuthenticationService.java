package fr.epita.kesKonAVu.application.security;

import fr.epita.kesKonAVu.domain.user.Member;

public interface AuthenticationService {

    Member findMemberByPseudo (String pseudo);
}
