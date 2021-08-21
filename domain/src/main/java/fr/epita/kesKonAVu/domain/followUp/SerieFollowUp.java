package fr.epita.kesKonAVu.domain.followUp;

import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeFollowUp;
import fr.epita.kesKonAVu.domain.common.ResourceTypeException;
import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeStatusEnum;
import fr.epita.kesKonAVu.domain.resource.Serie;

import javax.persistence.*;
import java.util.Set;

//@Entity
public class SerieFollowUp extends FollowUp {

    @OneToMany (cascade = CascadeType.ALL)
    @JoinTable(name="suiviSerieProgression")
    private Set<EpisodeFollowUp> episodeFollowUps;

    public SerieFollowUp(){
    }

    //getters et setters

    public Set<EpisodeFollowUp> getEpisodeFollowUps() {
        return episodeFollowUps;
    }

    public void setEpisodeFollowUps(Set<EpisodeFollowUp> episodeFollowUps) {
        this.episodeFollowUps = episodeFollowUps;
    }


}
