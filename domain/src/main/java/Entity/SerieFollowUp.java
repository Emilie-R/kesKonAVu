package Entity;

import Enums.EnumStatus;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class SerieFollowUp extends MovieFollowUp {
    private Float progression;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<EpisodeFollowUp> episodeFollowUps;
    private Serie serie;

    public SerieFollowUp(){
    }

    public void CalculateStatus(){
        //If at least one episodeFollow-up unseen, then SerieFollow-up status = "A VOIR"
        if (this.getStatus() != EnumStatus.VU.toString()) {
            long result = 0;
            result = episodeFollowUps.stream()
                    .filter(e -> e.getStatus() == EnumStatus.AVOIR.toString())
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
                    .filter(e -> e.getStatus() == EnumStatus.AVOIR.toString())
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
