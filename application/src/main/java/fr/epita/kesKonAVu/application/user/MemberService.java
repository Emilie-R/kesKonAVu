package fr.epita.kesKonAVu.application.user;

import fr.epita.kesKonAVu.domain.user.Member;

public interface MemberService {

    /**
     * Create a new member
     * @param member the member to create
     * @return the created Member
     * RunTimeException : AlreadyExistingException + NotFoundException + DataFormatException
     */
    Member createMember(Member member);

    /**
     *  Update the member data
     * @param member to update
     *
     */
    void updateMember(Member member);

    /**
     * Retrieve a member given the identifier
     * @param id the member identifier
     * @return the retrieved member or NotFoundException if no member found
     */
    Member findOne(Long id);
}
