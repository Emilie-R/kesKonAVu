import java.time.LocalDate;
import java.util.List;

public abstract class Resource {
    private int id;
    private String title;
    private String externalId;
    private String year;
    private String pictureUrl;
    private String synopsis;
    private String actors;
    private String category;
    private String director;
    private LocalDate creationDate;
    private List<Evaluation> evaluations;
    private List<UserProgression> userProgressions;
    private List<User> users;

    void Evaluate(){
        //à coder
    }

    void ModifyEvaluation(){
        //à coder
    }
    void DeleteEvaluation(){
        //à coder
    }

}
