package fr.epita.kesKonAVu.domain.resource;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Serie extends Resource {

    private Integer numberOfSeasons;
    private int numberOfEpisodes;

    @OneToMany (cascade = CascadeType.ALL)
    @JoinTable(name = "SerieEpisode")
    private Set<Episode> episodes;

    // constructeurs
    public Serie(){
    }

    // getters et setters

    public Integer getNumberOfSeasons ( ) {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons (Integer numberOfSeasons) {
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
