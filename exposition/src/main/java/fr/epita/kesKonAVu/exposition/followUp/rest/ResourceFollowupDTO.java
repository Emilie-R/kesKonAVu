package fr.epita.kesKonAVu.exposition.followUp.rest;

import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import fr.epita.kesKonAVu.exposition.member.rest.MemberDTO;
import fr.epita.kesKonAVu.exposition.resource.rest.ResourceDTO;

import java.time.LocalDate;

public class ResourceFollowupDTO {
    private Long idFollowUp;
    private ResourceDTO resourceDTO;

    private StatusEnum status;
    private LocalDate lastModificationDate;
    private int note;
    private MemberDTO memberDTO;

    public ResourceFollowupDTO(){
    }

    public Long getIdFollowUp ( ) {
        return idFollowUp;
    }

    public void setIdFollowUp (Long idFollowUp) {
        this.idFollowUp = idFollowUp;
    }

    public ResourceDTO getResourceDTO ( ) {
        return resourceDTO;
    }

    public void setResourceDTO (ResourceDTO resourceDTO) {
        this.resourceDTO = resourceDTO;
    }

    public StatusEnum getStatus ( ) {
        return status;
    }

    public void setStatus (StatusEnum status) {
        this.status = status;
    }

    public LocalDate getLastModificationDate ( ) {
        return lastModificationDate;
    }

    public void setLastModificationDate (LocalDate lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public int getNote ( ) {
        return note;
    }

    public void setNote (int note) {
        this.note = note;
    }

    public MemberDTO getMemberDTO ( ) {
        return memberDTO;
    }

    public void setMemberDTO (MemberDTO memberDTO) {
        this.memberDTO = memberDTO;
    }
}
