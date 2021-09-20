package fr.epita.kesKonAVu.domain.user;

public interface MemberDomainService {

    /**
     * allow to control the password Format
     *
     * @param password password to create in repository
     * @return True : the member password is OK
     */
    Boolean checkMemberPassword(String password);

    /**
     * allow to control the pseudo format
     *
     * @param pseudo pseudo to create in repository
     * @return True : the member pseudo is OK
     */

    Boolean checkMemberPseudo(String pseudo);

    /**
     * allow to create a new member from an existing one
     * @param memberToDuplicate Member to diplicate
     * @return the copy of the member
     */

    Member duplicateMember(Member memberToDuplicate);
}
