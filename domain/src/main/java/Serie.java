import java.util.List;

public class Serie extends Resource{
    private int numberOfSeasons;
    private List<Season> seasons;



    public void CalculateSerieStatus(){
        // à coder
    }
    public float CalculateUserProgression(){
        // à coder
        return 0;
    }
    // constructeurs
    public Serie(){
    }

    public Serie (int numberOfSeasons, List<Season> seasons) {
        this.numberOfSeasons = numberOfSeasons;
        this.seasons = seasons;
    }

    // getters et setters
    public int getNumberOfSeasons ( ) {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons (int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public List<Season> getSeasons ( ) {
        return seasons;
    }

    public void setSeasons (List<Season> seasons) {
        this.seasons = seasons;
    }
}
