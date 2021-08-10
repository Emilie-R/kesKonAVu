package fr.epita.kesKonAVu.domain.Entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Serie extends Resource {

    private int numberOfSeasons;
    private int numberOfEpisodes;

    @OneToMany
    private Set<Episode> episodes;

    // constructeurs
    public Serie(){
    }

    // getters et setters

    public int getNumberOfSeasons ( ) {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons (int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public int getNumberOfEpisodes ( ) {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes (int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public Set<Episode> getEpisodes ( ) {
        return episodes;
    }

    public void setEpisodes (Set<Episode> episodes) {
        this.episodes = episodes;
    }
}