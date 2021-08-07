import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Serie extends Resource{
    private int numberOfSeasons;
    private List<Season> seasons;

    public void CalculateSerieStatusAndProgressionUser(int idOfConnectedUser){
        //Search correspondent userProgression for the serie
        List<UserProgression> userAdvancement;
        userAdvancement = this.getUserProgressions().stream()
                .filter(e -> e.getUser().getId() == idOfConnectedUser)
                .distinct()
                .collect(Collectors.toList());
        //Calculate serie status
        long numberOfNonViewedSeasons = 0;
        numberOfNonViewedSeasons = seasons.stream()
                .filter(season -> season.getStatus() == Status.AVOIR.toString())
                .count();
        if (numberOfNonViewedSeasons>0){
            //Apply Status.AVOIR on the matched userProgression object of the serie
            if(userAdvancement.size() != 0) {
                userAdvancement.stream().forEach(e -> e.setStatus(Status.AVOIR.toString()));
            }
        }
        //Find unviewed episodes for the serie
        List<Episode> episodesOfTheSerie = null;
        Stream<Season> streamOfSeasons = seasons.stream();
        streamOfSeasons.forEach(e -> e.getEpisodes()
                .forEach(f -> episodesOfTheSerie.add(f)));
        //Count total number of episodes for the serie
        long totalNumberOfEpisodes = streamOfSeasons.mapToInt(e -> e.getNumberOfEpisodes())
                .sum();
        //Count unviewed episodes for the serie
        long numberOfNonViewedEpisodes = episodesOfTheSerie.stream()
                .filter(e -> e.getStatus() == Status.AVOIR.toString())
                .count();
        //Calculate the user progression for the serie
        float progression = 0;
        if(totalNumberOfEpisodes > 0) {
            progression = numberOfNonViewedEpisodes / totalNumberOfEpisodes;
            if(userAdvancement.size() != 0) {
                final float result = progression;
                userAdvancement.stream().forEach(e -> e.setProgression(result));
            }
        }
    }

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

    public List<Season> getSeasons ( ) {
        return seasons;
    }

    public void setSeasons (List<Season> seasons) {
        this.seasons = seasons;
    }
}
