package fr.epita.kesKonAVu.domain.followUp;

import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeFollowUp;
import fr.epita.kesKonAVu.domain.common.ResourceTypeException;
import fr.epita.kesKonAVu.domain.resource.Serie;

import javax.persistence.*;
import java.util.Set;

@Entity
public class SerieFollowUp extends ResourceFollowUp {

    private Float progression;

    @OneToMany
    @JoinTable(name="suiviSerieProgression")
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
     public void CalculateProgression(){
         long episodesViewed = 0;

         if (this.getResource() instanceof Serie) {
             episodesViewed = episodeFollowUps.stream()
                     .filter(e -> e.getStatus() == StatusEnum.VU)
                     .count();
             if (((Serie) this.getResource()).getNumberOfEpisodes() > 0 ) {
                 this.progression = Float.valueOf(100 * episodesViewed / ((Serie) this.getResource()).getNumberOfEpisodes());
             } else {this.progression = 0F;}

         } else {
             throw new ResourceTypeException();
         }
    }


}
