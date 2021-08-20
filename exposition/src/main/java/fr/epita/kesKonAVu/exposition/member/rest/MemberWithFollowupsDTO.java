package fr.epita.kesKonAVu.exposition.member.rest;

import fr.epita.kesKonAVu.exposition.followUp.rest.FollowupDTO;

import java.util.Set;

public class MemberWithFollowupsDTO {

    private Long idMember;

    private Set<FollowupDTO> resourceFollowUpS;

    public Set<FollowupDTO> getResourceFollowUpS ( ) {
        return resourceFollowUpS;
    }

    public void setResourceFollowUpS (Set<FollowupDTO> resourceFollowUpS) {
        this.resourceFollowUpS = resourceFollowUpS;
    }


    public Long getIdMember ( ) {
        return idMember;
    }

    public void setIdMember (Long idMember) {
        this.idMember = idMember;
    }
}
