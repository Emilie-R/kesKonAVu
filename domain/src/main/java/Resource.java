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

    //getters et setters
    public int getId ( ) {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getTitle ( ) {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getExternalId ( ) {
        return externalId;
    }

    public void setExternalId (String externalId) {
        this.externalId = externalId;
    }

    public String getYear ( ) {
        return year;
    }

    public void setYear (String year) {
        this.year = year;
    }

    public String getPictureUrl ( ) {
        return pictureUrl;
    }

    public void setPictureUrl (String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getSynopsis ( ) {
        return synopsis;
    }

    public void setSynopsis (String synopsis) {
        this.synopsis = synopsis;
    }

    public String getActors ( ) {
        return actors;
    }

    public void setActors (String actors) {
        this.actors = actors;
    }

    public String getCategory ( ) {
        return category;
    }

    public void setCategory (String category) {
        this.category = category;
    }

    public String getDirector ( ) {
        return director;
    }

    public void setDirector (String director) {
        this.director = director;
    }

    public LocalDate getCreationDate ( ) {
        return creationDate;
    }

    public void setCreationDate (LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<Evaluation> getEvaluations ( ) {
        return evaluations;
    }

    public void setEvaluations (List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public List<UserProgression> getUserProgressions ( ) {
        return userProgressions;
    }

    public void setUserProgressions (List<UserProgression> userProgressions) {
        this.userProgressions = userProgressions;
    }

    public List<User> getUsers ( ) {
        return users;
    }

    public void setUsers (List<User> users) {
        this.users = users;
    }
}
