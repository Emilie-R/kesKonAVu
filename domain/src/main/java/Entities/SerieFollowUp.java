package Entities;

import Enums.EnumStatus;

import javax.persistence.*;
import java.util.List;

@Entity
public class SerieFollowUp extends ResourceFollowUp {
    private Float progression;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "serieFollowUp")
    private List<EpisodeFollowUp> episodeFollowUps;

    private Serie serie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    public SerieFollowUp(){
    }

    public void CalculateStatus(){
        //If at least one episodeFollow-up unseen, then SerieFollow-up status = "A VOIR"
        if (this.getStatus() != EnumStatus.VU.toString()) {
            long result = 0;
            result = episodeFollowUps.stream()
                    .filter(e -> e.getStatus() == false)
                    .count();
            if(result == 0){
                this.setStatus(EnumStatus.VU.toString());
            }
        }
    }

    public void CalculateProgression(){
        if (this.progression <= 100.0f) {
            long episodesUnviewed = 0;
            episodesUnviewed = episodeFollowUps.stream()
                    .filter(e -> e.getStatus() == false)
                    .count();

            if(this.serie.getNumberOfEpisodes() != 0){
                this.setProgression(100.0f - episodesUnviewed * 100.0f / this.serie.getNumberOfEpisodes());
            }
        }

    }
    //getters et setters

    public Float getProgression ( ) {
        return progression;
    }

    public void setProgression (Float progression) {
        this.progression = progression;
    }

    public List<EpisodeFollowUp> getEpisodeFollowUps ( ) {
        return episodeFollowUps;
    }

    public void setEpisodeFollowUps (List<EpisodeFollowUp> episodeFollowUps) {
        this.episodeFollowUps = episodeFollowUps;
    }

    public Serie getSerie ( ) {
        return serie;
    }

    public void setSerie (Serie serie) {
        this.serie = serie;
    }
}
