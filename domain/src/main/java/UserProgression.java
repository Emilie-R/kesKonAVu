import java.time.LocalDate;

public class UserProgression {
    private int id;
    private String status;
    private float progression;
    private Serie serie;
    private Movie movie;
    private User user;
    private LocalDate creationDate;
    private LocalDate lastModificationDate;

    //constructeurs

    public UserProgression(){

    }
    //getters et setters

    public int getId ( ) {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getStatus ( ) {
        return status;
    }

    public void setStatus (String status) {
        this.status = status;
    }

    public LocalDate getCreationDate ( ) {
        return creationDate;
    }

    public void setCreationDate (LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getLastModificationDate ( ) {
        return lastModificationDate;
    }

    public void setLastModificationDate (LocalDate lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public Serie getSerie ( ) {
        return serie;
    }

    public void setSerie (Serie serie) {
        this.serie = serie;
    }

    public Movie getMovie ( ) {
        return movie;
    }

    public void setMovie (Movie movie) {
        this.movie = movie;
    }

    public User getUser ( ) {
        return user;
    }

    public void setUser (User user) {
        this.user = user;
    }

    public float getProgression ( ) {
        return progression;
    }

    public void setProgression (float progression) {
        this.progression = progression;
    }
}
