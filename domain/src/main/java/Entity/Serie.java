package Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Serie extends Movie {
    private int numberOfSeasons;
    private int numberOfEpisodes;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<Episode> episodes;

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

    public List<Episode> getEpisodes ( ) {
        return episodes;
    }

    public void setEpisodes (List<Episode> episodes) {
        this.episodes = episodes;
    }
}
