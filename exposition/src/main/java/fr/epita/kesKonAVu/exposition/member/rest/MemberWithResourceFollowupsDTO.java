package fr.epita.kesKonAVu.exposition.member.rest;

import fr.epita.kesKonAVu.exposition.followUp.rest.ResourceFollowupDTO;

import java.util.Set;

public class MemberWithResourceFollowupsDTO {

    private Long idMember;

    private Set<ResourceFollowupDTO> resourceFollowUpS;

    public Set<ResourceFollowupDTO> getResourceFollowUpS ( ) {
        return resourceFollowUpS;
    }

    public void setResourceFollowUpS (Set<ResourceFollowupDTO> resourceFollowUpS) {
        this.resourceFollowUpS = resourceFollowUpS;
    }


    public Long getIdMember ( ) {
        return idMember;
    }

    public void setIdMember (Long idMember) {
        this.idMember = idMember;
    }
}
