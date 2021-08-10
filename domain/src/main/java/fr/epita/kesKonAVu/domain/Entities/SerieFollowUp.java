package fr.epita.kesKonAVu.domain.Entities;

import fr.epita.kesKonAVu.domain.Enums.EnumStatus;

import javax.persistence.*;
import java.util.Set;

@Entity
public class SerieFollowUp extends ResourceFollowUp {

    private Float progression;

    @OneToMany
    private Set<EpisodeFollowUp> episodeFollowUps;

    public SerieFollowUp(){
    }

    //getters et setters

    public Float getProgression() {
        return progression;
    }

    public void setProgression(Float progression) {
        this.progression = progression;
    }

    public Set<EpisodeFollowUp> getEpisodeFollowUps() {
        return episodeFollowUps;
    }

    public void setEpisodeFollowUps(Set<EpisodeFollowUp> episodeFollowUps) {
        this.episodeFollowUps = episodeFollowUps;
    }

    /**
     *
     */
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

    /**
     *
     */
//   public void CalculateProgression(){
//        if (this.progression <= 100.0f) {
//            long episodesUnviewed = 0;
//            episodesUnviewed = episodeFollowUps.stream()
//                    .filter(e -> e.getStatus() == false)
//                    .count();
//
//            if(this.getResource().getNumberOfEpisodes() != 0){
//                this.setProgression(100.0f - episodesUnviewed * 100.0f / this.serie.getNumberOfEpisodes());
//            }
//        }
//
//    }


}
