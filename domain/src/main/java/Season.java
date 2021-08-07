import java.util.List;

public class Season {
    private int id;
    private int number;
    private String status;
    private int numberOfEpisodes;
    private List<Episode> episodes;

    public void CalculateSeasonStatus(){
        if(status == Status.AVOIR.toString()) {
            long numberOfNonViewedEpisodes = 0;
            numberOfNonViewedEpisodes = episodes.stream()
                    .filter(episode -> episode.getStatus() == Status.AVOIR.toString())
                    .count();
            if (numberOfNonViewedEpisodes == 0) {
                this.status = Status.VU.toString();
            }
        }

    }

    // constructeurs
    public Season(){

    }

    public Season (int id, int number, int numberOfEpisodes) {
        this.id = id;
        this.number = number;
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public Season (int id, int number, String status, int numberOfEpisodes, List<Episode> episodes) {
        this.id = id;
        this.number = number;
        this.status = status;
        this.numberOfEpisodes = numberOfEpisodes;
        this.episodes = episodes;
    }

    //getters et setters
    public int getId ( ) {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public int getNumber ( ) {
        return number;
    }

    public void setNumber (int number) {
        this.number = number;
    }

    public String getStatus ( ) {
        return status;
    }

    public void setStatus (String status) {
        this.status = status;
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
