import java.time.LocalDate;

public class Evaluation {
    private int id;
    private int note;
    private LocalDate lastModificationDate;
    private Serie serie;
    private Movie movie;
    private User user;

    public Evaluation(){

    }

    public Evaluation (int id, int note, LocalDate lastModificationDate, Serie serie, Movie movie, User user) {
        this.id = id;
        this.note = note;
        this.lastModificationDate = lastModificationDate;
        this.serie = serie;
        this.movie = movie;
        this.user = user;
    }

    public int getId ( ) {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public int getNote ( ) {
        return note;
    }

    public void setNote (int note) {
        this.note = note;
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
}
