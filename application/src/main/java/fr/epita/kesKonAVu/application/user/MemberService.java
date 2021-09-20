package fr.epita.kesKonAVu.application.user;

import fr.epita.kesKonAVu.domain.resource.ResourceTypeEnum;
import fr.epita.kesKonAVu.domain.user.Member;

import java.util.Optional;

public interface MemberService {

    /**
     * Create a new member
     *
     * @param member the member to create
     * @return the created Member
     * RunTimeException : AlreadyExistingException + NotFoundException + DataFormatException
     */
    Member createMember(Member member);

    /**
     * Retrieve a member given the pseudo of the member
     *
     * @param pseudo the pseudo of the member to be found
     * @return the retrieved member or NotFoundException if no member found
     *
     */
    Member findOne(String pseudo);

    /**
     *
     * @param id id de l'utilisateur
     * @return renvoie l'utilisateur avec ses ResourceFollowUps
     *
     */
    Member findByIdWithAllResourceFollowUps(String id, Optional<ResourceTypeEnum> typeResource);


    /**
     *  TODO
     *  Update the member data
     *
     * @param member to update
     *
     */
    void updateMember(Member member);
}
