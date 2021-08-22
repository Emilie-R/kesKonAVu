package fr.epita.kesKonAVu.domain.user;

import org.springframework.stereotype.Service;

@Service
public class MemberDomainServiceImpl implements MemberDomainService{

    private static final int PSEUDO_SIZE_MINI = 6;
    private static final int PASSWORD_SIZE_MIN = 6;

    @Override
    public Boolean checkMemberPassword(String password) {
        return password.replaceAll(" ", "").length() >= PASSWORD_SIZE_MIN;
    }

    @Override
    public Boolean checkMemberPseudo(String pseudo) {
        return pseudo.replaceAll(" ", "").length() >= PSEUDO_SIZE_MINI;
    }
}
