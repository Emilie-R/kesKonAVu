package fr.epita.kesKonAVu.exposition.followUp.rest;

import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public class FollowUpUpdateDTOLight {

    private Long idMember;

    @NotNull
    private Integer note;

    private StatusEnum status;

    public Long getIdMember() {
        return idMember;
    }

    public void setIdMember(Long idMember) {
        this.idMember = idMember;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Integer getNote ( ) {
        return note;
    }

    public void setNote (Integer note) {
        this.note = note;
    }
}
