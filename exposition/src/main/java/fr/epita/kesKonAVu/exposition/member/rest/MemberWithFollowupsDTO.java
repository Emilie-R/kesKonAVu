package fr.epita.kesKonAVu.exposition.member.rest;

import fr.epita.kesKonAVu.exposition.followUp.rest.FollowUpDTO;

import java.util.Set;

public class MemberWithFollowupsDTO {

    private Set<FollowUpDTO> resourceFollowUpS;

    public Set<FollowUpDTO> getResourceFollowUpS ( ) {
        return resourceFollowUpS;
    }

    public void setResourceFollowUpS (Set<FollowUpDTO> resourceFollowUpS) {
        this.resourceFollowUpS = resourceFollowUpS;
    }

}
